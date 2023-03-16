package com.example.simpleMall.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/8/2022, Saturday
 **/
@Entity
@Getter
@Setter
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NotNull
    private Long customerId;

    @Column
    @NotNull
    private Long productId;

    @Column
    @NotNull
    private Date orderDate;

    /**
     * status of order
     * ORDERED - SHIPPED - RECEIVED - COMPLETED
     */
    @Column
    @NotNull
    private String orderStatus = "ORDERED";

    @Column
    @NotNull
    private String paymentInfo;





}
