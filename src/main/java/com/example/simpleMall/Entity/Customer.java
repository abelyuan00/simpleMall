package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Customer extends BasicUser{

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private Boolean membership;

    @Column
    private Integer phoneNumber;

    @Column
    private String extraInformation;

}
