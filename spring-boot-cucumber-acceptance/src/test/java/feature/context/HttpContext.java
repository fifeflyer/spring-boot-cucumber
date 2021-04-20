package feature.context;

import feature.annotation.SpringScenarioScope;
import lombok.Data;

@Data
@SpringScenarioScope
public class HttpContext {

    private String requestDetails;
    private String responseDetails;
}

