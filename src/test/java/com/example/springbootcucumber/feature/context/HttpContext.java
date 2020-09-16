package com.example.springbootcucumber.feature.context;

import com.example.springbootcucumber.feature.annotation.SpringScenarioScope;
import lombok.Data;

@Data
@SpringScenarioScope
public class HttpContext {

    private String requestDetails;
    private String responseDetails;
}

