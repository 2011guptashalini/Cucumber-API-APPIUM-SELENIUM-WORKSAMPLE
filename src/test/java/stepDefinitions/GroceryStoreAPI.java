package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddClientResponse;
import pojo.GetStatusResponse;
import resources.APIResources;
import resources.APIResponses;
import resources.TestDataBuild;
import resources.Utils;


public class GroceryStoreAPI extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	String cartId;


@Given("Status API")
public void status_api() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res=given().spec(requestSpecification());
}
@When("user hit {string} using HTTP {string} request")
public void user_hit_using_http_request(String resource, String method) {
    // Write code here that turns the phrase above into concrete actions
	APIResources resourceAPI=APIResources.valueOf(resource);
	System.out.println(resourceAPI.getResource());
	
	
	resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	if(method.equalsIgnoreCase("POST"))
	 response =res.when().post(resourceAPI.getResource());
	else if(method.equalsIgnoreCase("GET"))
		 response =res.when().get(resourceAPI.getResource());
	else if(method.equalsIgnoreCase("DELETE"))
		 response =res.when().delete(resourceAPI.getResource());
	else if(method.equalsIgnoreCase("PATCH"))
		 response =res.when().patch(resourceAPI.getResource());
	else if(method.equalsIgnoreCase("PUT"))
		 response =res.when().put(resourceAPI.getResource());
}
@Then("validate response in status API")
public void validate_response_in_status_api() {
    // Write code here that turns the phrase above into concrete actions
	GetStatusResponse resp = response.getBody().as(GetStatusResponse.class);
	String status = resp.getStatus();
	assertEquals(status,"UP");
}


/*@Then("validate response in status API")
public void in_response_of_status_api_is(String keyValue, String expectedValue) {
    // Write code here that turns the phrase above into concrete actions
	var resp = response.getBody().as(GetStatusResponse.class);
	resp.getStatus();
	assertEquals(getJsonPath(response,keyValue),expectedValue);
}*/

@Then("{string} in response body is {string}")
public void in_response_body_in(String keyValue, String expectedValue) {
    // Write code here that turns the phrase above into concrete actions
	assertEquals(getJsonPath(response,keyValue),expectedValue);
}

@Then("status code is {int}")
public void status_code_is(Integer expectedStatusCode) {
	
    // Write code here that turns the phrase above into concrete actions
	//AddClientResponse resp = response.as(AddClientResponse.class);
	assertEquals(Integer.valueOf(response.getStatusCode()),Integer.valueOf(expectedStatusCode));
}

@Given("Registering Client API")
public void registering_client_api() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res=given().spec(requestSpecification()).body(data.AddClientPayload());
}

@Given("Registering Client API {string} {string}")
public void registering_client_api(String clientName, String clientEmail) throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res=given().spec(requestSpecification()).body(data.AddClientInvalidPayload(clientName, clientEmail));
}

@Given("create cart Api")
public void create_cart_api() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res=given().spec(requestSpecification());
}
@Then("{string} in response body is not Null")
public void in_response_body_is_not_null(String keyValue) {
    // Write code here that turns the phrase above into concrete actions
	String value = getJsonPath(response,keyValue).toString();
	assertFalse(value.length()== 0);
}



}


