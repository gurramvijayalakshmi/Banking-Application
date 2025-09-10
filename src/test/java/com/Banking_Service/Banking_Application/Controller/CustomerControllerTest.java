package com.Banking_Service.Banking_Application.Controller;

import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerPersonalInfo customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new CustomerPersonalInfo();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerPersonalInfo> customers = Arrays.asList(customer);
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerPersonalInfo>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetCustomerById() {
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        ResponseEntity<CustomerPersonalInfo> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testCreateCustomer() {
        when(customerService.createCustomer(customer)).thenReturn(customer);

        ResponseEntity<CustomerPersonalInfo> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testUpdateCustomer() {
        when(customerService.updateCustomer(1L, customer)).thenReturn("Customer Updated");

        ResponseEntity<String> response = customerController.updateCustomer(1L, customer);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Customer Updated", response.getBody());
    }

    @Test
    void testDeleteCustomer() {
        when(customerService.deleteCustomer(1L)).thenReturn("Deleted");

        ResponseEntity<String> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }
}
