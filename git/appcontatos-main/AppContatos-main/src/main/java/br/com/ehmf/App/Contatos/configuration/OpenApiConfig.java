package br.com.ehmf.App.Contatos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		System.out.println("Hello dear!");
		
		return new OpenAPI()
				.components(
						new Components().addSecuritySchemes("bearerAuth", 
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
									.scheme("bearer").bearerFormat("JWt")))
				.info(new Info()
						.title("App de cadastro de contatos")
						.description("Este aplicativo faz controle de cadastro de contatos.")
						.contact(new Contact()
									.name("ELEN CRISTINA PINHEIRO")
									.email("elencp87@gmail.com")
									.url("http://localhost:8081/swagger-ui/index.html")
								)
						.version("Versão 0.0.1-SNAPSHOT"))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
}
