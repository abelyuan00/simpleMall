package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Entity
@Getter
@Setter
@Table
public non-sealed class Customer extends User{

    @Column
    @NotNull
    private Integer membershipLevel = 0;

    @Column
    private String address;

    @Column
    private String name;



    public Customer() {
        setRole("customer");
        setStatus("normal");
    }


}
