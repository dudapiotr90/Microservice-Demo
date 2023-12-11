package pl.dudi.departmentservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentServiceConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Department Service REST APIs")
                .description("Department Service Rest APIs Documentation")
                .version("v1.0")
                .contact(new Contact()
                    .email("piotrjavatestowy@gmail.com")
                    .name("Piotr")
                    .url("https://github.com/dudapiotr90")
                )
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://github.com/dudapiotr90"))
            )
            .externalDocs(new ExternalDocumentation()
                .description("Department-Service Doc")
                .url("https://github.com/dudapiotr90")
            );
    }
}

