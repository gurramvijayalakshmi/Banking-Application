package com.Banking_Service.Banking_Application.Aduit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class Auditable {

    @Column(name = "create_user_id", updatable = false)
    private String createUserId;
    @Column(name = "create_ts", updatable = false)
    private LocalDateTime createTs;
    @Column(name = "update_user_id")
    private String updateUserId;
    @Column(name = "update_ts")
    private LocalDateTime updateTs;

    @PrePersist
    protected void onCreate(){
        this.createTs= LocalDateTime.now();
        this.updateTs=createTs;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateTs = LocalDateTime.now();
    }
}
