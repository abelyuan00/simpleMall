package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
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
    @NotNull
    private String code;

    @Column
    private Date updateTime;

    /**
     * normal or locked
     */
    @Column
    private String status = "normal";

    @Column
    @NotNull
    private String productName;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private Date createTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorId", referencedColumnName = "id")
    private Admin admin;

    @Column

    public void generateCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date =sdf.format(new Date());
        code = productName+date;
    }
}
