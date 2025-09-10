package com.Banking_Service.Banking_Application.Dao;

import com.Banking_Service.Banking_Application.Model.CustomerPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerPersonalInfo, Long> {
}
