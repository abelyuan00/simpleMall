package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "loginName" }) })
public non-sealed class Admin extends User{

//    @Column
//    @NonNull
//    private String adminCode;

    public Admin() {
        setRole("admin");
        setStatus("normal");
    }
}
