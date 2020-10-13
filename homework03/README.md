# Flitter web service

Flitter is a messenger, where you can send flits. You need to implement Back-end for Flitter.

### Required functionality
- User authorization
- Post flits
- Discover flits - show flits from other users
- View flits from other user
- Subscribe to other users
- Watch user subscribers
- View my personal flit feed

### User identification
User registers with name and receives unique token. This token can be used to authenticate user in other APIs.

### Returned objects

Each service accepts some arguments and returns the object of the following type:
```json
{
  "data": <Method result> ,
  "errorMessage": <Error message>
}
```
For example, method "Register user" (/user/register) in case of success may return the following object:
```json
{
  "data": {
    "userName": "Sasha",
    "userToken": "7b74505e-e07a-4544-b060-909956d2161c"
  },
  "errorMessage": null
}
```
In case of error:
```json
{
  "data": null,
  "errorMessage": "This name is already taken"
}
```

In the description of each service, point "Returns" describes the object which must be included in the `data` field in a successful case.

### List of required services

* "/clear": completely resets the state of the application (deletes all users, all flits, etc.)
    - Method: DELETE   
    - Accepts: nothing  
    - Returns: anything  
    - **Note**: this service is used by our testing system, please, implement it first.  
   
-----------------------------------------

* "/user/register": adds new user with the specified name and assigns him a randomly generated token. 
    - Method: POST   
    - Accepts: object like `{"userName": <name>}`  
    - Returns: object like  `{"userName": <name>, "userToken": <token>}`.
    - **Note**: token must be a string
    ```
    curl -X POST 'localhost:8080/user/register' -d '{"userName": "Sasha"}' -H "Content-Type: application/json"
    ```
  - Successful result:
    ```json
    {
      "data":{
        "userName":"Sasha",
        "userToken":"7b74505e-e07a-4544-b060-909956d2161c"
      }
    }
    ```
  - Error:
    ```json
    {
      "data": null,
      "errorMessage": "This name is already taken"
    }
    ```
-----------------------------------------
    
* "/user/list": returns the list of names of all registered users.
    - Method: GET   
    - Accepts: nothing
    - Returns: list of user names.
    ```shell script
    curl 'localhost:8080/user/list'
    ```

-----------------------------------------

* "/flit/add": adds new flit from user with the specified token. 
    - Method: POST   
    - Accepts: object like `{"userToken": <token>, "content": <flits content>}`
    - Returns: anything
    ```shell script
    curl -X POST 'localhost:8080/flit/add' -d '{"userToken": "7b74505e-e07a-4544-b060-909956d2161c", "content": "Hello!"}' -H "Content-Type: application/json"
    ```
    
-----------------------------------------

* "/flit/discover": returns 10 last flits from list of all flits.  
    - Method: GET   
    - Accepts: nothing
    - Returns: list of objects like `{"userName": <name>, "content": <flits content>}`

-----------------------------------------

* "/flit/list/{username}": returns the list of all flits of a specified user.
    - Method: GET   
    - Accepts: path param "username"
    - Returns: list of objects like `{"userName": <name>, "content": <flits content>}`

-----------------------------------------

* "/flit/list/feed/{usertoken}": returns flit feed for specified user. 
    - Method: GET   
    - Accepts: path param "usertoken"
    - Returns: list of objects like `{"userName": <name>, "content": <flits content>}`
  
-----------------------------------------

* "/subscribe": subscribes one user to another user. 
    - Method: POST   
    - Accepts: object like `{"subscriberToken": <subscriber token>, "publisherName": <publisher name>}`  
    - Returns: anything

-----------------------------------------

* "/unsubscribe": removes subscription.
    - Method: POST   
    - Accepts: object like `{"subscriberToken": <subscriber token>, "publisherName": <publisher name>}`  
    - Returns: anything

-----------------------------------------

* "/subscribers/list/{usertoken}": returns list names of subscribers of the specified user.
    - Method: GET   
    - Accepts: path param "usertoken"
    - Returns: list names of subscribers

-----------------------------------------

* "/publishers/list/{userToken}": returns list names of publishers of the specified user.
    - Method: GET   
    - Accepts: path param "usertoken"
    - Returns: list names of publishers