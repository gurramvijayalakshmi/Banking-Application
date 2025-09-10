package com.Banking_Service.Banking_Application.Controller;

import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.AccountService;
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

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private CustomerAccInfo customerAccInfo;
    private CustomerPersonalInfo customerPersonalInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerPersonalInfo = new CustomerPersonalInfo();
        customerPersonalInfo.setCustomerId(1L);

        customerAccInfo = new CustomerAccInfo();
        customerAccInfo.setCustomer(customerPersonalInfo);
        customerAccInfo.setAccountType("Savings");
        customerAccInfo.setAmountDeposited(5000.0);
    }

    @Test
    void testGetAllCustomerAcc() {
        List<CustomerAccInfo> accounts = Arrays.asList(customerAccInfo);
        when(accountService.getAllCustomerAcc()).thenReturn(accounts);

        ResponseEntity<List<CustomerAccInfo>> response = accountController.getAllCustomerAcc();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testCreateAccount() {
        when(accountService.createAccount(1L, "Savings", 5000.0, customerAccInfo)).thenReturn(customerAccInfo);

        ResponseEntity<CustomerAccInfo> response = accountController.createAccount(customerAccInfo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Savings", response.getBody().getAccountType());
    }

    @Test
    void testGetAccountById() {
        when(accountService.getAccountById(1L)).thenReturn(customerAccInfo);

        ResponseEntity<CustomerAccInfo> response = accountController.getAccountById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getCustomer().getCustomerId());
    }

    @Test
    void testUpdateAccount() {
        when(accountService.updateAccount(1L, customerAccInfo)).thenReturn(customerAccInfo);

        ResponseEntity<CustomerAccInfo> response = accountController.updateAccount(1L, customerAccInfo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Savings", response.getBody().getAccountType());
    }

    @Test
    void testDeleteCustomerAccount() {
        when(accountService.deleteCustAccount(1L)).thenReturn("Account Deleted");

        ResponseEntity<String> response = accountController.deleteCustomerAccount(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account Deleted", response.getBody());
    }
}
