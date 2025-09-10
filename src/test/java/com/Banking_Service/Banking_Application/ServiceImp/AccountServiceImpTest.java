package com.Banking_Service.Banking_Application.ServiceImp;

import com.Banking_Service.Banking_Application.Dao.AccountDao;
import com.Banking_Service.Banking_Application.Dao.CustomerDao;
import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
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

class AccountServiceImpTest {

    @InjectMocks
    private AccountServiceImp accountService;

    @Mock
    private AccountDao accountDao;

    @Mock
    private CustomerDao customerDao;

    private CustomerAccInfo customerAccInfo;
    private CustomerPersonalInfo customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new CustomerPersonalInfo();
        customer.setCustomerId(1L);

        customerAccInfo = new CustomerAccInfo();
        customerAccInfo.setAccountId(100L);
        customerAccInfo.setCustomer(customer);
        customerAccInfo.setAccountType("Savings");
        customerAccInfo.setAmountDeposited(1000.0);
    }

    @Test
    void testGetAllCustomerAcc() {
        when(accountDao.findAll()).thenReturn(Arrays.asList(customerAccInfo));

        List<CustomerAccInfo> result = accountService.getAllCustomerAcc();

        assertEquals(1, result.size());
        verify(accountDao, times(1)).findAll();
    }

    @Test
    void testCreateAccount() {
        when(customerDao.existsById(1L)).thenReturn(true);
        when(accountDao.existsByCustomerIdAndAccountType(1L, "Savings")).thenReturn(false);
        when(customerDao.findById(1L)).thenReturn(Optional.of(customer));
        when(accountDao.save(any(CustomerAccInfo.class))).thenReturn(customerAccInfo);

        CustomerAccInfo result = accountService.createAccount(1L, "Savings", 1000.0, customerAccInfo);

        assertNotNull(result);
        assertEquals("Savings", result.getAccountType());
    }

    @Test
    void testGetAccountById() {
        when(accountDao.findById(100L)).thenReturn(Optional.of(customerAccInfo));

        CustomerAccInfo result = accountService.getAccountById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getAccountId());
    }

    @Test
    void testUpdateAccount() {
        when(accountDao.findById(100L)).thenReturn(Optional.of(customerAccInfo));
        when(accountDao.save(any(CustomerAccInfo.class))).thenReturn(customerAccInfo);

        CustomerAccInfo result = accountService.updateAccount(100L, customerAccInfo);

        assertNotNull(result);
        assertEquals("Savings", result.getAccountType());
    }

    @Test
    void testDeleteCustAccount() {
        doNothing().when(accountDao).deleteById(100L);

        String result = accountService.deleteCustAccount(100L);

        assertEquals("Deleted", result);
        verify(accountDao, times(1)).deleteById(100L);
    }
}