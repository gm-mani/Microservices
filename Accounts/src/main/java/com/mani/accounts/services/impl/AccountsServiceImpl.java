package com.mani.accounts.services.impl;

import com.mani.accounts.constants.AccountsConstants;
import com.mani.accounts.dtos.AccountsDto;
import com.mani.accounts.dtos.CustomerDto;
import com.mani.accounts.entity.Accounts;
import com.mani.accounts.entity.Customer;
import com.mani.accounts.exceptions.CustomerAlreadyExistsException;
import com.mani.accounts.exceptions.ResourceNotFoundException;
import com.mani.accounts.mappers.AccountsMapper;
import com.mani.accounts.mappers.CustomerMapper;
import com.mani.accounts.repository.AccountRepository;
import com.mani.accounts.repository.CustomerRepository;
import com.mani.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with phone number already exists" + customer.getMobileNumber());
        }
        customerRepository.save(customer);
        accountRepository.save(createNewAccount(customer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();

        accounts.setCustomerId(customer.getCustomerId());
        Long accountNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);

        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        var accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId())
        );

        CustomerDto customerDto = CustomerMapper.toCustomerDto(customer);
        customerDto.setAccountsDto(AccountsMapper.toAccountsDto(accounts));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            var accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber())
            );
            AccountsMapper.toAccounts(accountsDto, accounts);
            accountRepository.save(accounts);

            var customerId = accounts.getCustomerId();
            var customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.toCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        customerRepository.deleteById(customer.getCustomerId());
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        return true;
    }

}
