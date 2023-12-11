package pl.dudi.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
    info = @Info(
        title = "Employee Service REST APIs",
        description = "Employee Service REST APIs documentation",
        version = "v1.0",
        contact = @Contact(
            name = "Piotr",
            email = "piotrjavatestowy@gmail.com",
            url = "https://github.com/dudapiotr90"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://github.com/dudapiotr90"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Employee Service Doc",
        url = "https://github.com/dudapiotr90/Microservice-Demo"
    )
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

}
