package br.com.ehmf.App.Contatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.ehmf.App.Contatos.resource", "br.com.ehmf.App.Contatos.service"})
@ComponentScan(basePackages = {"br.com.ehmf.App.Contatos.resource", "br.com.ehmf.App.Contatos.configuration"})
public class AppContatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppContatosApplication.class, args);
	}

}
