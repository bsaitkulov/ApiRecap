package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.builder.Vote;
import org.example.controller.VotesController;
import org.example.entities.Votes;
import org.example.utils.ObjectConverter;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Random;

public class Votes_Steps {

    private VotesController votesController;
    private Response response;
    private Votes[] votes;
    private String randomId;

    @Given("a base URL {string} with header key {string} and header value {string}")
    public void a_base_url_with_header_key_and_header_value(String baseURL, String headerKey, String headerValue) {
     votesController = new VotesController(baseURL, headerKey, headerValue);
    }
    @When("I send GET request with endpoint {string}")
    public void i_send_get_request_with_endpoint(String endpoint) {
        response = votesController.get(endpoint);
    }
    @Then("I have to store response Body and verify response result is more than {int}")
    public void i_have_to_store_response_body_and_verify_response_result_is_more_than(int zero) {
        votes = response.getBody().as(Votes[].class);
        Assert.assertTrue(votes.length > zero);

    }
    @Then("I should verify status code is {int}")
    public void i_should_verify_status_code_is(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @When("I select a random voteID from stored votes")
    public void i_select_a_random_vote_id_from_stored_votes() {
        Random random = new Random();
        int index = random.nextInt(votes.length);
        Votes selectedId = votes[index];
        randomId = String.valueOf(selectedId.getId());
    }
    @Then("I send GET request with randomID to {string}")
    public void i_send_get_request_with_random_id_to(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", randomId);
        response = votesController.get(updatedEndpoint);
    }

    @Then("Verify response body not empty and  response should match the corresponding given object")
    public void verify_response_body_not_empty_and_response_should_match_the_corresponding_given_object() {
        Votes voteBody = response.getBody().as(Votes.class);
        String actualId = voteBody.getId();
        Assert.assertNotNull(voteBody);
        Assert.assertEquals(actualId,randomId);
    }

    @When("I create a new vote using POST request to {string}")
    public void i_create_a_new_vote_using_post_request_to(String endpoint) throws JsonProcessingException {
        String votePayload = "{\n" +
                "    \"image_id\": \"qwerty1\",\n" +
                "    \"sub_id\": \"my-user-366\",\n" +
                "    \"value\": \"17\"\n" +
                "}";
//        String voteJson = ObjectConverter.convertJavaToJson(Vote.createVote());
//        System.out.println(voteJson);
       response = votesController.post(endpoint, votePayload);

    }
    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
       int actualStatusCode = response.getStatusCode();
       Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }
    @Then("The response match expected value message {string}")
    public void the_response_match_expected_value_message(String message) {
       Votes bodySuccessMessage = response.getBody().as(Votes.class);
       String successMessage = bodySuccessMessage.getMessage();
       Assert.assertEquals(successMessage,message);
       Assert.assertNotNull(bodySuccessMessage.getId());
    }
}
