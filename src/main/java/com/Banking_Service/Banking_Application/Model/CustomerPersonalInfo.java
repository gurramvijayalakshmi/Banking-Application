package com.Banking_Service.Banking_Application.Model;

import com.Banking_Service.Banking_Application.Aduit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_personal_info")
public class CustomerPersonalInfo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String address;
//    @Pattern(regexp = "\\d+")
    private String contactDetails;
}
