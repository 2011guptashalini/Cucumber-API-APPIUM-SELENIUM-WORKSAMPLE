@APITest
Feature: Validating Grocery store APIs

Scenario: Verify status API
	Given Status API
	When user hit "getStatusResponse" using HTTP "GET" request
	Then validate response in status API
	And status code is 200
	
Scenario: Verify Registering client API 
	Given Registering Client API
	When user hit "postRegisterClient" using HTTP "POST" request
	Then status code is 201
	And "accessToken" in response body is not Null
	
Scenario Outline: Verify Registering client API with existing email
	Given Registering Client API "<clientName>" "<clientEmail>"
	When user hit "postRegisterClient" using HTTP "POST" request
	Then status code is 409
	
Examples:
    |clientName   |  clientEmail   |
    |Test         |  test@test.com |
    |Test         |  test@test.com |
    
Scenario Outline: Verify Registering client API with No email
	Given Registering Client API "<clientName>" "<clientEmail>"
	When user hit "postRegisterClient" using HTTP "POST" request
	Then status code is 400
	
Examples:
    |clientName   |  clientEmail   |
    |Test         |                |
    
Scenario Outline: Verify Registering client API with no client
	Given Registering Client API "<clientName>" "<clientEmail>"
	When user hit "postRegisterClient" using HTTP "POST" request
	Then status code is 400
	
	
Examples:
    |clientName   |  clientEmail   |
    |             |  test@test.com |
    
    
Scenario: Verify Create Cart API
	Given create cart Api
	When user hit "postCart" using HTTP "POST" request
	Then "created" in response body is "true"
	And "cartId" in response body is not Null
	And status code is 201
    

    

    
    
	
	
	

	
	