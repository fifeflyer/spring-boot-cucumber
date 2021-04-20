package feature.filter;

import static java.lang.System.lineSeparator;
import static java.util.Objects.isNull;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.annotation.SpringScenarioScope;
import feature.context.HttpContext;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringScenarioScope
public class HttpLoggingFilter  extends RequestLoggingFilter implements Filter{

    private final HttpContext httpContext;
    private final ObjectMapper objectMapper;

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpecification,
            FilterableResponseSpecification responseSpecification,
            FilterContext filterContext) {

        Response response = filterContext.next(requestSpecification, responseSpecification);

        httpContext.setRequestDetails(divider() +
                lineSeparator() +
                "HTTP REQUEST" +
                divider() +
                buildItem("Request", (requestSpecification.getMethod() + " " + requestSpecification.getURI())) +
                buildItem("Form Parameters", requestSpecification.getFormParams()) +
                buildItem("Request Parameters", requestSpecification.getRequestParams()) +
                buildItem("Headers", requestSpecification.getHeaders()) +
                buildItem("Cookies", requestSpecification.getCookies()) +
                buildItem("Proxy", requestSpecification.getProxySpecification()) +
                buildItem("Body", requestSpecification.getBody()));

        try {
            httpContext.setResponseDetails(divider() +
                    lineSeparator() +
                    "HTTP RESPONSE" +
                    divider() +
                    buildItem("Status", response.getStatusLine()) +
                    buildItem("Cookies", response.getDetailedCookies()) +
                    buildItem("Content Type", response.getContentType()) +
                    buildItem("Headers", response.getHeaders()) +
                    buildItem("Body", objectMapper.writeValueAsString(objectMapper.readTree(response.getBody().asString()))));
        } catch (JsonProcessingException e) {
            // Do nothing!
        }

        return response;
    }

    private String buildItem(String name, Object o) {
        return isNull(o) ? "" : lineSeparator() + name + ": " + o.toString();
    }

    private String divider() {
        return lineSeparator() + StringUtils.repeat("-", 100);
    }
}
