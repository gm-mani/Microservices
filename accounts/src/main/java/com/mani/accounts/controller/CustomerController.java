package com.mani.accounts.controller;


import com.mani.accounts.dtos.CustomerDetailsDto;
import com.mani.accounts.dtos.ErrorResponseDto;
import com.mani.accounts.services.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "REST API for CustomerDetails",
        description = "REST APIs in General Bank to FETCH customer details"
)
@RestController
@RequestMapping("/api")
@Validated
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer, account, cards and loans details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam
                                                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                          String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = customerServiceImpl.getCustomerDetails(mobileNumber);
        return ResponseEntity.ok().body(customerDetailsDto);
    }
}
