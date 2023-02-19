package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 2/10/2023, Friday
 **/

@Entity
@Getter
@Setter
@Table
public class CustomerResources {

    //resource ID
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    //link to subscription file relative path
    @Column
    private String subFilePath;

    //How much bandwidth have been consumed
    @Column
    private Double usedData;

    @Column
    private Double quota;

    //Generated in mapper
    @Column
    private Date createdTime;

    //Generated in mapper
    @Column
    private Date updateTime;

    @Column
    private String picPath;

}
