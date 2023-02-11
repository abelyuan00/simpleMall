package com.example.simpleMall.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

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
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @Column
    private String subFileAddress;

    @Column
    private Double usedData;

}
