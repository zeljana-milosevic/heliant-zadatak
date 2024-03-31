package com.heliant.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   OpenAPI defineOpenApi() {
       Server server = new Server();
       server.setUrl("http://localhost:8080");
       server.setDescription("Heliant - Spring zadatak");

       Contact myContact = new Contact();
       myContact.setName("Zeljana Milosevic");
       myContact.setEmail("zeljana.milosevic94@gmail.com");

       Info information = new Info()
               .title("Heliant API")
               .version("1.0")
               .description("API za upravljanje formularima.")
               .contact(myContact);
       return new OpenAPI().info(information).servers(List.of(server));
   }
}
