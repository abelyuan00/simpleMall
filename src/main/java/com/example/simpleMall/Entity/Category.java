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
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 1 shoe - 2 sneaker - 3 nike
     */
    @Column
    @NotNull
    private Integer categoryLevel = 1;

    /**
     * id of upper class(shoe - sneaker - Nike)
     */
    @Column
    private Long superiorId;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column
    @NotNull
    private Boolean isDeleted;

    @Column
    @NotNull
    private Long creatorId;

    @Column
    @NotNull
    private Date creatTime;

    @Column
    private Long editorId;

    @Column
    private Date editTime;


}
