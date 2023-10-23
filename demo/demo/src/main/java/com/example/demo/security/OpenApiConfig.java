package com.example.demo.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =  @Info(
		contact = @Contact(
				name = "Mustafa Can Yılmaz",
				email = "mustafacanyilmaaz@gmail.com"
				
				),
		description = "Upload and Download Document Demo Example",
		title = "Upload and Download Document Demo Example - Mustafa Can Yılmaz",
		version = "2.0"
		), 
		servers = {
				@Server(
						description = "Local ENV",
						url = "http://localhost:8080"
						)
				
				
		}
		
		)
public class OpenApiConfig {

}
