package feature.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import com.example.springbootcucumber.SpringBootCucumberApplication;
import feature.filter.HttpLoggingFilter;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SpringBootCucumberApplication.class, CucumberSpringConfiguration.Configuration.class}, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class CucumberSpringConfiguration {

    @Autowired
    private HttpLoggingFilter httpLoggingFilter;

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("----- Executing Cucumber Test [{}] -----", scenario.getName());

        RestAssured.filters(httpLoggingFilter);
        RestAssured.baseURI = "http://localhost:8080";
    }

    @TestConfiguration
    @ComponentScan({"com.example.springbootcucumber", "feature"})
    public static class Configuration {

    }
}
