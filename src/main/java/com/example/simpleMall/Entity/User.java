package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@MappedSuperclass
@Getter
@Setter
public sealed class User permits Admin, Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Date createdTime;

    @Column
    private Date updateTime;

    @Column
    @NotNull
    private String password;

    @Column
    private String code;

    @Column(unique = true)
    @NotNull
    private String loginName;

    //locked or normal
    @Column
    private String status;

    //customer or admin
    @Column(nullable = false)
    private String role;

    public void generateCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date =sdf.format(new Date());
        Random random = new Random();
        code = role+Math.abs(random.nextInt())+loginName+date;
    }

    public void encodePassword(String raw){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(raw);
        //        Boolean isPasswordMatches = encoder.matches("123456", password);
    }
}
