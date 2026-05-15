package com.mani.accounts.services;

import com.mani.accounts.dtos.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto getCustomerDetails(String mobileNumber, String correlationId);
}
