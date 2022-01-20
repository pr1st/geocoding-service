# Example application (GeoCoding service)
Initial task: https://github.com/waliot/test-tasks/blob/master/tasks/backend-1.md

# Cache Database
Redis

# Endpoint
/api/v1/geo either of this parameters
 - ?address={addressString} (getting data from address)
 - ?x={xDouble}&y={yDouble} (getting data from coordinates)

# Application is not 100% complete but is working
### TODO features list
 - Add tests
 - Make more thoughts about where and what to log
 - Add descriptive error response on all use cases (for example: if external api returned not parsable data it should be understandable)
 - Move to GEO redis api (with possibility to more nicely index coordinates)
 - Add different external api and ability to chose (maybe Google)
 - Think about different producing result of different apis
 - Cleanup code a little (Some class names are not representative enough)
 - Add Mapstruct instead of manual mapping to dto
 - Add address aliases for more cache hits (e.g. searching "city" produces response address "country-district-city")
 - Look into spring cache abstraction library, might be will be useful
 - Add Dockerfile which will produce compressed image using modules
 - Customize Ttl for cache entities
 - Add link to coordinate producer which will redirect to their map with this address
 - Make more descriptive about application README