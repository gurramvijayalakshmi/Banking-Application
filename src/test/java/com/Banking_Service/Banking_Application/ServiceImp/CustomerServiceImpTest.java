package com.Banking_Service.Banking_Application.ServiceImp;

import com.Banking_Service.Banking_Application.Dao.CustomerDao;
import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImpTest {

    @InjectMocks
    private CustomerServiceImp customerService;

    @Mock
    private CustomerDao customerDao;

    private CustomerPersonalInfo customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new CustomerPersonalInfo();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setAddress("123 Street, City");
        customer.setContactDetails("9876543210");
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerPersonalInfo> customers = Arrays.asList(customer);
        when(customerDao.findAll()).thenReturn(customers);

        List<CustomerPersonalInfo> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetCustomerById() {
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));

        CustomerPersonalInfo result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testCreateCustomer() {
        when(customerDao.save(customer)).thenReturn(customer);

        CustomerPersonalInfo result = customerService.createCustomer(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testUpdateCustomer_Success() {
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));
        when(customerDao.save(any(CustomerPersonalInfo.class))).thenReturn(customer);

        String result = customerService.updateCustomer(1L, customer);

        assertEquals("Success", result);
    }

    @Test
    void testUpdateCustomer_CustomerNotFound() {
        when(customerDao.findById(1L)).thenReturn(Optional.empty());

        String result = customerService.updateCustomer(1L, customer);

        assertNull(result);
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerDao).deleteById(1L);

        String result = customerService.deleteCustomer(1L);

        assertEquals("Deleted", result);
    }
}
