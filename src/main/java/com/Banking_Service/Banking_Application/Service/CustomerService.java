package com.Banking_Service.Banking_Application.Service;

import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;

import java.util.List;

public interface CustomerService {
    List<CustomerPersonalInfo> getAllCustomers();

    CustomerPersonalInfo getCustomerById(Long customerId);

    CustomerPersonalInfo createCustomer(CustomerPersonalInfo customerPersonalInfo);

    String updateCustomer(Long id, CustomerPersonalInfo customerPersonalInfo);

    String deleteCustomer(Long id);
}
