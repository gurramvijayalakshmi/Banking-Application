package com.Banking_Service.Banking_Application.Controller;


import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/banking/customer")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/Account")
    public ResponseEntity<List<CustomerAccInfo>> getAllCustomerAcc(){
        try{
            return new ResponseEntity<>(accountService.getAllCustomerAcc(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createAccount")
    public ResponseEntity<CustomerAccInfo> createAccount(@RequestBody CustomerAccInfo customerAccInfo){
        try{
            CustomerPersonalInfo customer = customerAccInfo.getCustomer();
            Long customerId = customer.getCustomerId();
            return new ResponseEntity<>(accountService.createAccount(customerId,customerAccInfo.getAccountType(),customerAccInfo.getAmountDeposited(),customerAccInfo),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<CustomerAccInfo> getAccountById(@PathVariable Long accountId){
        try{
            return new ResponseEntity<>(accountService.getAccountById(accountId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("Update/{accountId}")
    public ResponseEntity<CustomerAccInfo> updateAccount(@PathVariable Long accountId,@RequestBody CustomerAccInfo customerAccInfo){
        CustomerAccInfo updatedAccount = accountService.updateAccount(accountId,customerAccInfo);
        if(updatedAccount != null){
            return new ResponseEntity<>(updatedAccount,HttpStatus.OK);
        }
        else
        {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<String> deleteCustomerAccount(@PathVariable("accountId") Long accountId){
        try{
            return new ResponseEntity<>(accountService.deleteCustAccount(accountId),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
