# GrailsDemoAddressBookApp
- This a demo AddressBook web app built using Grails. 
- The domain class is Address with three attributes and some constraints on them.
- The CURD operations list/read is available to any user without authentication. 
- Operations create, update, delete is only allowed to authenticated users with role ROLE_WRITE
- The bootstapping creates a user "writer" with password "writer" belonging to ROLE_WRITE
- There are some basic unit tests for the domain and controller and itegration test for the service.
