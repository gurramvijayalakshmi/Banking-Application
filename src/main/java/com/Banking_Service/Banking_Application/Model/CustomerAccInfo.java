package com.Banking_Service.Banking_Application.Model;

import com.Banking_Service.Banking_Application.Aduit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_account_info")
public class CustomerAccInfo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerPersonalInfo customer;
    private String accountType;
    private Double amountDeposited;
}
