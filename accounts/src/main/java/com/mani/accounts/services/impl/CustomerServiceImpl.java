package com.mani.accounts.services.impl;

import com.mani.accounts.dtos.CardsDto;
import com.mani.accounts.dtos.CustomerDetailsDto;
import com.mani.accounts.dtos.LoansDto;
import com.mani.accounts.exceptions.ResourceNotFoundException;
import com.mani.accounts.feignclient.CardsFeignClient;
import com.mani.accounts.feignclient.LoansFeignClient;
import com.mani.accounts.mappers.AccountsMapper;
import com.mani.accounts.mappers.CustomerMapper;
import com.mani.accounts.repository.AccountRepository;
import com.mani.accounts.repository.CustomerRepository;
import com.mani.accounts.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto getCustomerDetails(String mobileNumber, String correlationId) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        var accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.toCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.toAccountsDto(accounts));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
