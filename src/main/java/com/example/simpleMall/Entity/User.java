package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String code;

    @Column
    private Date createdTime;

    @Column
    private Date modifiedTime;

    @Column
    private String passwd;

    @Column
    private String name;

    //locked or normal
    @Column
    private String status;

    //customer or admin
    @Column
    private String role;

    public String getCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date());
        return new String(sdf.toString()+id+role);
    }
}
