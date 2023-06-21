# AddressBook Spring MVC application

This application contains the Address Book example.
A Form is created inside resources/templates/add-address.html. This will add new address to table.which contains three input field (name,email,phone number)
resources/templates/index.html contains the data table list which input is created in add-address.html file. 
resources/templates/update-address.html allow user to edit and delete details created in add-address.html

##CSS

For styling purpose i have used Bootstrap.

## Properties

resources/application.properties file contains the database connection details, It's AWS RDS MySQL instance
It contains a table called address inside adress_book schema.

## AddressController

controller is first layer to interact with HTML code
controller will provide all API for the application 
it includes update, edit ,search ,list of address and delete.
This java file also includes dependency injection.

## validation 

I have added validation for name @Size(min = 2, max = 40), for email its @Email and for phone number its @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}").

## Test

AddressBookControllerTest has tested all the used API. This will performs listing address, adding address, edit, update, and delete
And also checks their response.
I have also tested page count that will check how many times page is called. which will give pageHits. 

## Filter by Name

In AddressController i have added filter which provides address using name attribute. Using this name you can filter information. 

## Address

In address file i have added three fields: Name, Email and Phone number.

## Page count

Added an API to count number of page hits, this API is called asynchronously every 3 seconds and result is displayed in every page.


