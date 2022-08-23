package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BasicUser  {

    @Id
    @Column(name = "id", nullable = false)
    Long id;

    @Column
    String Type;

    @Column
    Integer uuid ;

    @Column
    String code;

    @Column
    Date createdTime;

    @Column
    Date modifiedTime;

    @Column
    String passwd;

    public String getCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date());
        return new String(sdf.toString()+uuid);
    }

    public Integer getUUid(){
        return (int) Math.random();
    }

    public String passwdEncryption(String passwd){
        return Base64.getEncoder().encodeToString(String.valueOf(passwd).getBytes());

    }

}
