package com.Banking_Service.Banking_Application.ServiceImp;

import com.Banking_Service.Banking_Application.Dao.AccountDao;
import com.Banking_Service.Banking_Application.Dao.CustomerDao;
import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CustomerDao customerDao;


    @Override
    @Transactional
    public List<CustomerAccInfo> getAllCustomerAcc() {
        return accountDao.findAll();
    }

    @Override
    @Transactional
    public CustomerAccInfo createAccount(Long customerId, String accountType, Double amountDeposited, CustomerAccInfo customerAccInfo) {
        if (!customerDao.existsById((customerId))) {
            throw new IllegalArgumentException("Customer Not Found");
        }
        if (accountDao.existsByCustomerIdAndAccountType(customerId, accountType)) {
            throw new IllegalArgumentException("already exists");
        }
        CustomerPersonalInfo customer = customerDao.findById(customerId).get();

        CustomerAccInfo account = new CustomerAccInfo();
        account.setAccountId(customerAccInfo.getAccountId());
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setCreateUserId(customerAccInfo.getCreateUserId());
        account.setUpdateUserId(customerAccInfo.getUpdateUserId());
        accountDao.save(account);
        return account;

    }

    @Transactional
    @Override
    public List<CustomerAccInfo> getAllCustomers() {
        return accountDao.findAll();
    }

    @Override
    @Transactional
    public CustomerAccInfo getAccountById(Long accountId) {
        return accountDao.findById(accountId).get();
    }

    @Override
    @Transactional
    public CustomerAccInfo updateAccount(Long accountId, CustomerAccInfo customerAccInfo) {
        CustomerAccInfo account = accountDao.findById(accountId).orElse(null);
        if (account != null) {
            account.setAccountType(customerAccInfo.getAccountType());
            account.setAmountDeposited(customerAccInfo.getAmountDeposited());
            account.setUpdateUserId(customerAccInfo.getUpdateUserId());
            account.setUpdateTs(customerAccInfo.getUpdateTs());
            return accountDao.save(account);
        } else {
            System.out.println("Account not fount for this id :: " + accountId);
            return null;
        }
    }

    @Override
    @Transactional
    public String deleteCustAccount(Long accountId) {
        accountDao.deleteById(accountId);
        return "Deleted";
    }
}
