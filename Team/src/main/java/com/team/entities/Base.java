package com.team.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

// base class ını entity e miras vermek için @MappedSuperclass kullanılır.
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Base {
    @CreatedBy
    @JsonIgnore
    private String createdBy;
    @CreatedDate
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy",timezone = "Europe/Istanbul")
    private Date createdDate;
    @LastModifiedBy
    @JsonIgnore //  kullanıcıya gösterilmesini istediğimiz alanlar için bu anotasyonu ekleriz.
    private String lastModifiedBy;
    @LastModifiedDate
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy",timezone = "Europe/Istanbul")
    private Date lastModifiedDate;
    private Boolean status;
}
