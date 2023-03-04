package com.example.simpleMall.Entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.UUID;

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

    @Column(unique = true,nullable = false)
    private String email;
    @Column
    private final String uuid;

    public Admin() {
        setRole("admin");
        setStatus("normal");
        uuid = String.valueOf(UUID.randomUUID());
    }


}
