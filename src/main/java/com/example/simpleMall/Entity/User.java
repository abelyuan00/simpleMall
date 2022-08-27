package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NotNull
    private String code;

    @Column
    private Date createdTime;

    @Column
    private Date updateTime;

    @Column
    @NotNull
    private String passwd;

    @Column
    private String name;

    //locked or normal
    @Column
    private String status = "normal";

    //customer or admin
    @Column
    @NotNull
    private String role = "customer";

    public String getCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date =sdf.format(new Date());
        return new String(date+name+role);
    }
}
