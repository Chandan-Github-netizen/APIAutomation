package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;

public class Demo {

    private ValidatableResponse validatableResponse;
    private String endpoint = "http://localhost:3000/users";

    @Given("I send a request to the URL to get user details")
    public void sendRequest(){
        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(endpoint).then();

        System.out.println("Response :"+validatableResponse.extract().asPrettyString());
    }

    @Then("the response will return status {int} and id {int} and salary {int} and name {string} and age {int} and message {string}")
    public void verifyStatus(int statusCode, int id, int emp_Salary, String firstname, int emp_age, String message ){

        validatableResponse.assertThat().statusCode(statusCode);
        validatableResponse.assertThat().body("firstName[0]",equalTo(firstname));


    }

    @When("Client sends a request to the URL to patch user details {string} and {string}")
    public void patchRequest(String firstName, String lastName){
        JSONObject request=new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        baseURI = "http://localhost:3000/";

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type", "application/JSON").
                body(request.toJSONString()).
                when().
                patch("/users/6").
                then().
                log().all();
    }
    @Then("the details should be updated successfully {string} with status code as {int}")
    public void patchUser(String firstname, int statusCode){

        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(endpoint).then();

        validatableResponse.assertThat().statusCode(statusCode);
        validatableResponse.assertThat().body("firstName[4]",equalTo(firstname));
    }


    @When("Client sends a request to the URL to put user details {string} {string} and {int}")
    public void putRequest(String firstName, String lastName, int subjectID){
        JSONObject request=new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectID);

        baseURI = "http://localhost:3000/";

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type", "application/JSON").
                body(request.toJSONString()).
                when().
                put("/users/6").
                then().
                log().all();
    }
    @Then("the details should be replaced successfully {string} with status code as {int}")
    public void putUser(String firstname, int statusCode){

        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(endpoint).then();

        validatableResponse.assertThat().statusCode(statusCode);
        validatableResponse.assertThat().body("firstName[4]",equalTo(firstname));
    }



    @When("Client sends a request to the URL to post user details {string} {string} and {int}")
    public void postRequest(String firstName, String lastName, int subjectID){
        JSONObject request=new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", subjectID);
        request.put("subjectId", 1);

        baseURI = "http://localhost:3000/";

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type", "application/JSON").
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                log().all();
    }
    @Then("the details should be posted successfully {string} with status code as {int}")
    public void postUser(String firstname, int statusCode){

        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(endpoint).then();

        validatableResponse.assertThat().statusCode(statusCode);
        validatableResponse.assertThat().body("firstName[4]",equalTo(firstname));
    }


}
