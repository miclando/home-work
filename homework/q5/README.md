# Q5

##### Question
You are tasked with improving the efficiency of a cache heavy system, which has the following
properties/architecture:

The system has 2 components, a single instance backend and several frontend instances.
1. The backend generates data and writes it to a relational database that is replicated to
multiple data centers.
2. The frontends handle client requests (common web traffic based) by reading data from
the database and serving it. Data is stored in a local cache for an hour before it expires
and has to be retrieved again. The cache’s eviction policy is LRU based.

There are two issues with the implementation above:
1. It turns out that many of the database accesses are redundant because the underlying
data didn't actually change.
2. On the other hand, a change isn't reflected until the cache TTL elapses, causing
staleness issues.
Offer a solution that fixes both of these problems. How would the solution change if the data
was stored in Cassandra and not a classic database?

##### Answer


######Current Structure
![Alt text](image/current.jpg?raw=true "current structure")


######Suggested Structure
![Alt text](image/new.jpg?raw=true "suggested structure")

we will add a massage queue to the architecture, 
the beck end server will push notifications on values that were updated,
and the front end servers will subscribe to this notifications and update there cache according to the notification.

from the back end server perspective:
once a data was generated for write the backend will write it to the master Master DB and the replication to the slaves will begin.
once the write is complete a notification will be sent to the massage queue including the record that was whiten.

from the front end server perspective:
the front end will mange its cache,
on startup the cache will be empty, the frontend will register to receive notifications on write operations from the backend.
once a notification is received:
it will be stored in the local cache if the value was alread ther then it will be updated if not it will be add as a new recored and the oldest will be remove based on the LRU.
the front end behavuer will reamin the same incase of a miss it will go to the DB to retrive the recored and store it if size limit is reached the oldest iteam will be removed based on the LRU.
the ttl is no longer needed.



this solution solves the two issues:
1.  no redundant calls are made since the date is pushed to the frontend servers on change and they will have an accurate state of it.
a read will be done only in case the data is not in the cache.
the date will always be aligned with changes in the DB since the notification is sent only after a successful write.
2.  the ttl is not used any more, since the data does nto get stale as any chnages are pushed to the frontend.
if the recoded was not changed it will remain in the cache until evicted.


 
Notes:
- the front end will store the the record that was pushed to it even if it was not previously in the cache,
this is done to remove the race condition that can occur if a read request came after the notification was received and not stored and the replication to the slave was not yet complete.
if this happens an invalid recoded will be stored in the cache.

- the choice to send notification after the write was done, removes the the case where a write operation failed and the notification was already sent creating an inconsistency between the frontend cache and the DB.

- we can optimize the storage of records if the front end will check the date it is overwriting is the same as the one provided, based on a check sum of it,
 in this case nothing will be done to the record (this is an optimization).
 
if we replace the DB with Casandra:

in case the DB is replaced by Casandra it will mean that we can be sure that the write and read operations are consistent by using quorum level for both.
this will mean that the write operation will be successful only if enough replicas were writen successfully and a read will be sucsesful only if the same number of replicas had the same data.

this will insure that a read operation done after the write was complete will get the same data.
 
based on this we no longer need to store each notification update in the cache, since we no longer need to worried that a read operation done after writhe will produce an inconsistent result,
as it can happen with the replication to a slave an reading from it.
be removing this the coach will provide a better performance because values will remain there longer opposed to the case where items were evicted because of storing updates from the notification. 



