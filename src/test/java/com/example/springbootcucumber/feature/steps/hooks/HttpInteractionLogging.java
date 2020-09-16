package com.example.springbootcucumber.feature.steps.hooks;

import static java.util.Objects.nonNull;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import com.example.springbootcucumber.feature.context.HttpContext;
import io.cucumber.java8.En;

public class HttpInteractionLogging implements En {

    public HttpInteractionLogging(HttpContext httpContext) {
        After(scenario -> {
            String request = httpContext.getRequestDetails();
            String response = httpContext.getResponseDetails();

            if (nonNull(request)) {
                scenario.attach(request, TEXT_PLAIN_VALUE, "request");
            }

            if (nonNull(response)) {
                scenario.attach(response, TEXT_PLAIN_VALUE, "response");
            }
        });
    }
}
