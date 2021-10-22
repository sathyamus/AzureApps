package com.snsystems;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 * @author Sathya
 */
public class SayHello {
	
    /**
     * This function listens at endpoint "/api/sayHello". 
     * Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/sayHello
     * 2. curl {your host}/api/sayHello?name=HTTP%20Query
     */
    @FunctionName("sayHello")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", 
            methods = {HttpMethod.GET, HttpMethod.POST}, 
            authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("SN Systems --- sayHello :: Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Missing input !!!, Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name + "<br> Welcome to Function App").build();
        }

    }
}
