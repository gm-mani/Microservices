package com.mani.accounts.feignclient;


import com.mani.accounts.dtos.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("/api/fetch")
    ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("mybank-correlation-id") String correlationId,
                                              @RequestParam String mobileNumber);
}
