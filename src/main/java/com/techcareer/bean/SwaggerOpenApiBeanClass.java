package com.techcareer.bean;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// LOMBOK
@Log4j2 // for log

// http://localhost:4444/swagger-ui.html

// @Configuration: Spring Boota , Classın Bean nesnesi olması için kullanıyoruz.
@Configuration
public class SwaggerOpenApiBeanClass {
    // FIRST
    public void swaggerOpenApiBeforeBeanMethod() {
        log.info("swagger Open Api Before Bean başladı");
        System.out.println("swagger Open Api Before Bean başladı");
    }//end SwaggerOpenApiBeanClass

    // OpenAPI
    @Bean
    public OpenAPI swaggerOpenApiMethod() {
        return new OpenAPI().info(
                new Info()
                        .title("Techcareer")
                        .description("Techcareer Information")
                        .version("V1.0")
                        .contact(new Contact()
                                .name("Techcareer")
                                .url("https://www.techcareer.net/")
                                .email("support@techcareer.net"))

        );
    }//end swaggerOpenApiMethod

    // LAST
    public void swaggerOpenApiAfterBeanMethod() {
        log.info("swagger Open Api After Bean bitti");
        System.out.println("swagger Open Api After Bean bitti");
    }//end swaggerOpenApiAfterBeanMethod
}//end SwaggerOpenApiBeanClass
