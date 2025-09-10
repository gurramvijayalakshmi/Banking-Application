package com.Banking_Service.Banking_Application.Service;

import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AccountService {
    List<CustomerAccInfo> getAllCustomerAcc();

    CustomerAccInfo createAccount(Long customerId, String accountType, Double amountDeposited, CustomerAccInfo customerAccInfo);

    List<CustomerAccInfo> getAllCustomers();

    CustomerAccInfo getAccountById(Long accountId);

    CustomerAccInfo updateAccount(Long accountId, CustomerAccInfo customerAccInfo);

    String deleteCustAccount(Long accountId);
}
