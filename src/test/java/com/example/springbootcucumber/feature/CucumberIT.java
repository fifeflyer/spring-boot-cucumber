package com.example.springbootcucumber.feature;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:feature",
        glue = {"com.example.springbootcucumber.feature"},
        plugin = {
                "pretty",
                "timeline:target/site/cucumber/timeline-report",
                "html:target/site/cucumber/basic-report.html",
                "json:target/site/cucumber/cucumber-report.json"
        },
        tags = "not @wip")
public class CucumberIT {
}
