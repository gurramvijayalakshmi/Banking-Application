package com.Banking_Service.Banking_Application.Controller;

import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import com.Banking_Service.Banking_Application.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("Customer")
    //http://localhost:8080/banking/Customer
    public ResponseEntity<List<CustomerPersonalInfo>> getAllCustomers(){
        try {
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}")
    //http://localhost:8080/banking/1
    public ResponseEntity<CustomerPersonalInfo> getCustomerById(@PathVariable Long customerId){
        try{
            return new ResponseEntity<>(customerService.getCustomerById(customerId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/Customer")
    //http://localhost:8080/banking/Customer
    public ResponseEntity<CustomerPersonalInfo> createCustomer(@RequestBody CustomerPersonalInfo customerPersonalInfo){
        try{
            return new ResponseEntity<>(customerService.createCustomer(customerPersonalInfo),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/updateCustomer/{id}")
    //http://localhost:8080/banking/updateCustomer/2
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,@RequestBody CustomerPersonalInfo customerPersonalInfo){
        try{
            return new ResponseEntity<>(customerService.updateCustomer(id,customerPersonalInfo),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCustomer/{accountId}")
    //http://localhost:8080/banking/deleteCustomer/4
    public ResponseEntity<String> deleteCustomer(@PathVariable("accountId") Long id){
        try{
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
