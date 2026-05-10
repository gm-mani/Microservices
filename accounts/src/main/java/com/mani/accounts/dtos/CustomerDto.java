package com.mani.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer details"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "G M Mani"
    )
    @NotNull(message = "Customer name cannot be null")
    @Size(min = 5, max = 30, message = "Length of customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email Address of the Customer",
            example = "manimlmw@gmail.com"
    )
    @Email(message = "Email address should be valid value")
    @NotNull(message = "Email address cannot be null or empty")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",
            example = "7164823612"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
