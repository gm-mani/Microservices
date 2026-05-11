package com.mani.loans;

import com.mani.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(LoansContactInfoDto.class)
@EnableJpaAuditing(
        auditorAwareRef = "auditAwareImpl"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "General Bank Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "G M Mani",
                        email = "manimlmw@gmail.com",
                        url = "http://wwww.sampleInfo.com"
                )
        )
)
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
