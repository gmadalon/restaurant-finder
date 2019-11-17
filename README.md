Service to finder restaurants near you.
------------------------------------------

It was developed using JAVA 8 and Spring boot. 
There is authentication with Outh2.0 but the service for search is open for nonauthenticated users

You can start all components using docker-compose. To do it follow the steps bellow:

1 - Go to dir: restaurant-finder/src/main/resources/docker
2 - Execute: "docker-compose up"

You can get your access token using this HTTP request, I created a default user admin@admin.com:

curl -X POST "http://localhost:8081/oauth/token" -H "accept: */*" -H "Authorization: Basic Z21jYmFwcDpnbWNi" -d "password=changeyourpassword&username=admin@admin.com&grant_type=password&scope=read%20write&client_secret=gmcb&client_id=gmcbapp"

You need to get the first field from response: "access_token". This token is a JWT token. 


You can create another user using this HTTP Request:
curl -X POST "http://localhost:8081/user" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"teste@gmail.com\", \"password\": \"123\"}"


A swagger interface was created to test the application "restaurant-finder". Just call http://localhost:8080/ on web browser. Feel free to test all request, I populate just one restaurant but you can 
add more using the access token. The request /restaurants/search is open to noAuthenticated user.

I prepared some other components in my github porfile to create a Microservices Enviroment. https://github.com/gmadalon/spring-cloud-netflix
I can improve "restaurant-finder" and create a NetflixOSS enviroment with restaurante-finder if you wish.


The app was made using api-first concept, so first I created the file /restaurant-finder/src/main/resources/swagger/api.yml, using a maven plugin the controllers and DTOs was generated. That is good to decouple API from domain.

The request /restaurants/search is cached, during 2 minutes even a lot os request will perform well.

I choose in memory H2 database, it is easy to start and test application. 



