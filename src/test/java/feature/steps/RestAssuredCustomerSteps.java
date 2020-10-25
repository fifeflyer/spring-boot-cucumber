package feature.steps;

import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class RestAssuredCustomerSteps implements En {

    private String path;
    private Response response;

    public RestAssuredCustomerSteps() {
        Given("the customer endpoint exists", () -> path = "/api/v1/customers");

        When("a valid request is sent to retrieve all customers", () ->
                response = given()
                        .header("Accept", ContentType.JSON.getAcceptHeader())
                        .get(path));

        When("a valid request is sent to retrieve a customer with id {string}", (id) ->
                response = given()
                        .header("Accept", ContentType.JSON.getAcceptHeader())
                        .basePath(path)
                        .pathParam("id", id)
                        .get("/{id}"));

        Then("the response status code should be {int}", (Integer code) ->
                assertThat(response.getStatusCode(), is(equalTo(code))));

        Then("the response should have a length of {int}", (Integer length) ->
                assertThat(response.jsonPath().getList("$").size(), equalTo(length)));

        Then("the customer should have a name of {string}", (name) ->
                assertThat(response.jsonPath().get("name"), equalTo(name)));
    }
}

