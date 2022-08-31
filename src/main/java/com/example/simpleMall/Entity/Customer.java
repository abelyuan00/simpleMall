package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
public class Customer extends User{

    @Column
    @NotNull
    private Integer membershipLevel = 0;

    @Column
    private String address;

    @Column
    private String name;



}
