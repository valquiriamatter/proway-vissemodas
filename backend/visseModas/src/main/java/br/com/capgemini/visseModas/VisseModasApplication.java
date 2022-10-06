package br.com.capgemini.visseModas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport //dar suporte a paginacao
@EnableSwagger2
public class VisseModasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisseModasApplication.class, args);
	}

}
