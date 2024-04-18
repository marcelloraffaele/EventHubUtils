package com.rmarcello.eventhubutils;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {


    //@Bean
    //public GroupedOpenApi internalOpenApi() {
    //    
    //    return GroupedOpenApi.builder().group("internal")
    //        .addOpenApiMethodFilter( (m) -> m.getName().equals("GET") )
    //            .build();
    //}
//
    //@Bean
    //public GroupedOpenApi externalOpenApi() {
    //    return GroupedOpenApi.builder().group("external")
    //        .addOpenApiMethodFilter( (m) -> m.getName().equals("POST") )
    //            .build();
    //}
//
    
              

    //@Bean
	//public OpenAPI customOpenAPI() {
	//	OpenAPI openapi= new OpenAPI()
	//			.components(new Components())
	//			.info(new Info().title("Books API").version("1.0")
	//					.license(new License().name("Apache 2.0").url("http://springdoc.org")));
//
    //    //List<Server> servers = new ArrayList<>();
    //    //servers.add(new Server().description("server1").url("https://server1.test.priv"));
    //    //openapi.setServers( servers );
    //    //openapi.setPaths( createPaths(loadApiDefinitions()) );
    //    return openapi;
	//}
    
    
}//