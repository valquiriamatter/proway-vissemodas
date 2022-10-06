package br.com.capgemini.visseModas.configs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket visseModas(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.capgemini.visseModas")) //pacote raiz
                .paths(PathSelectors.ant("/**")) //pode ler tudo
                .build();
//                .ignoredParameterTypes(); //pra ignorar alguma coisa
    }

}
