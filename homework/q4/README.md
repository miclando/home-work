# Q4

##### Question
You're tasked with writing a spec for a generic local cache with the following property: If the
cache is asked for a key that it doesn't contain, it should fetch the data using an externally
provided function that reads the data from another source (database or similar).
What features do you think such a cache should offer? How, in general lines, would you
implement it?

##### Answer

Features:
1. set function: to allow definition of the function that will populate the cache, the function will receive the key and retrieve the data.
2. define value: a logic for defining how the value returned from the cache is represented.
3. set TTL: allow the user to set the time after which the cache retires reloads the value if none is set the value will remain.
4. cache preload: on start up the cache populates the values initial.
5. limit the number of entrees for performance purposes based on LRU.

the implementation would include a map representing the keys and the relevant values, the values will be nodes of a linked list.
each node will have a pre and next field for adding and removing items(double linked list implantation).
the cache will also hold pointer to the first and last items.
the map used should be thread safe to allow the map to be accessible by different threads.
the function to be called will be stored as a callback to be executed in case the value is not available.
the function will receive the key and execute the logic to retrieve the value, the returned value will be stored in the map.
the function will be called if the value is not in the cache and was accessed or after TTL expiration.
if the function did not retrieve any thing the value will be removed from the cache.
in the map we will store the TTL near the value so that on a retrieval we can check if the value is still valid or we need to call the callback.
the map will be key -> <ttl,data>.

we will mange a count for the number of items in the cache once the limit is reached we will remove the item the last is pointing to and update the last.
each time we will add a value we will update the head pointer.

the cache pre load will be implemented by running a thread that will populate the cache based on a query provided with the limit being the cache limit. 

the cache data structure can also be implemented by wrapping a LinkedListMap in Collections.synchronizedMap to create a synchronised data structure.


