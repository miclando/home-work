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
3. set TTL: allow the user to set the way the cache retires values
4. cache preload: on start up the cache populates the values initial.
5. limit the number of entrees for performance purposes.

the implementation would include a map representing the keys and the relevant values.
the map used should be thread safe to allow the map to be accessible by different threads.
the function to be called will be stored as a callback to be executed in case the value is not available.
the function will receive the key and execute the logic to retrieve the value, the returned value will be stored in the map.
in the map we will store the TTL near the value so that on a retrieval we can check if the value is still valid or we need to call the callback.
the map will be key -> <timestemp,ttl,data>.
we will mange a count for the number of items in the cache once the limit is reache a thread will be triggered wiche will check the 
time stemp in the chach to identify the oldest item and remove it
to support the limit we will add a hash set which i will syncronize to make it thread safe.
each time a new 


