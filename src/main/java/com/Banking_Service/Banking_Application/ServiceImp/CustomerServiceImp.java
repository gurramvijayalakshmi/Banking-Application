package com.Banking_Service.Banking_Application.ServiceImp;

import com.Banking_Service.Banking_Application.Dao.CustomerDao;
import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<CustomerPersonalInfo> getAllCustomers() {
        //return List.of();
        return customerDao.findAll();
    }

    @Override
    public CustomerPersonalInfo getCustomerById(Long customerId) {
        return customerDao.findById(customerId).get();
    }

    @Override
    public CustomerPersonalInfo createCustomer(CustomerPersonalInfo customerPersonalInfo) {
        return customerDao.save(customerPersonalInfo);
    }

    @Override
    public String updateCustomer(Long customerId, CustomerPersonalInfo customerPersonalInfo) {
        CustomerPersonalInfo customer = customerDao.findById(customerId).orElse(null);
        if(customer == null){
            System.out.println("Customer not found for this id :: " + customerId);
            return null;
        }
        customer.setName(customerPersonalInfo.getName());
        customer.setAddress(customerPersonalInfo.getAddress());
        customer.setContactDetails(customerPersonalInfo.getContactDetails());
        customerDao.save(customer);
        return "Success";
    }

    @Override
    @Transactional
    public String deleteCustomer(Long id) {
        customerDao.deleteById(id);
        return "Deleted";
    }
}
