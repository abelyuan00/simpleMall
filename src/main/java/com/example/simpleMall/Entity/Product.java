package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/28/2022, Wednesday
 **/
@MappedSuperclass
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //product code
    @Column
    private String code;

    @Column
    private Date updateTime;

    /**
     * normal or locked
     */
    @Column
    private String status;

    @Column
    private String productName;

    @Column
    private String description;

    public void generateCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date =sdf.format(new Date());
        code = productName+date;
    }
}
