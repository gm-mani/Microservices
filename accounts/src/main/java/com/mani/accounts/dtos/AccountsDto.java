package com.mani.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold the Account Information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of General Bank account",
            example = "1234567890"
    )
    @NotNull(message = "Account Number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of General Bank account",
            example = "Savings"
    )
    @NotNull(message = "Account Type can not be null")
    private String accountType;

    @Schema(
            description = "General Bank Branch address",
            example = "123 Bengaluru"
    )
    @NotNull(message = "Brnach Address can not be null")
    private String branchAddress;

}
