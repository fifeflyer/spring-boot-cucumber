package feature.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.example.springbootcucumber.web.model.CustomerDTO;
import io.cucumber.java8.En;

public class RestTemplateCustomerSteps implements En {

    private static final String ENDPOINT = "http://localhost:8080/api/v1/customers";

    private static final ParameterizedTypeReference<List<CustomerDTO>> CUSTOMERS = new ParameterizedTypeReference<>() {};

    private List<CustomerDTO> customers;

    public RestTemplateCustomerSteps(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        Given("^a request to retrieve all customers is executed$", () -> {
            customers = restTemplate.exchange(ENDPOINT, HttpMethod.GET, null, CUSTOMERS).getBody();
        });

        Then("all available customers are returned", () -> {
            assertThat(customers, hasSize(1));
        });
    }
}
