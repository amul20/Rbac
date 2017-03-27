# Rbac
A simple role based access control system

# Problem Statement - 

Implement a role based auth system. 

System should be able to assign a role to user and remove a user from the role.

Entities are USER, ACTION TYPE, RESOURCE, ROLE.

ACTION TYPE defines the access level(Ex: READ, WRITE, DELETE)

Access to resources for users are controlled strictly by the role.One user can have multiple roles. Given a user, action type and resource system should be able to tell whether user has access or not.

# Assumptions-
1) Only admin can add or remove a user from a role.
2) Role should already be existing in the database.
3) Admin is also already existing in the database.
4) User to be added or removed should be in the database.
5) Resource and Action to be used in query should already be there in the database.
6) All existing conditons are in par with successful response. The API works even if they are not present in the server just in this case we get an unsuccessful response.

# APIs -

# To Add a user to a role
POST  http://serverIp:8080/add/to/role/{uId}/{role}

uId - user Id to be added

role - in which role the user has to be added

Request Headers - 

adminId - the user Id of Admin

adminPassword - the password of admin

eg- curl -X POST -H "adminId: amulyam" -H "adminPassword: qwerty" -H "Cache-Control: no-cache" -H "http://localhost:8080/add/to/role/anmol/admin"

# To Remove a user from a role
DELETE  http://serverIp:8080/remove/from/role/{uId}/{role}

uId - user Id to be added

role - in which role the user has to be removed

Request Headers - 

adminId - the user Id of Admin

adminPassword - the password of admin

eg-  curl -X DELETE -H "adminId: amulyam" -H "adminPassword: qwerty" -H "Cache-Control: no-cache" -H "http://localhost:8080/remove/from/role/anmol/admin"

# To query if a user has action over a resource
GET http://serverIp:8080/query?uId={userid}&res={resource_name}&action={action_name}

eg - curl -X GET -H "Cache-Control: no-cache" -H "http://localhost:8080/query?uId=anmol&res=db&action=read"



Also sql files are attached in the resources folder which will create the tables and data for testing the APIs
