package com.Banking_Service.Banking_Application.Dao;

import com.Banking_Service.Banking_Application.Model.CustomerAccInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountDao extends JpaRepository<CustomerAccInfo, Long> {

    @Query(value = "SELECT COUNT(*) > 0 FROM customer_account_info WHERE customer_id = :customerId AND account_type =:accountType", nativeQuery = true)

    boolean existsByCustomerIdAndAccountType(@Param("customerId") long customerId,@Param("accountType") String accountType);
}
