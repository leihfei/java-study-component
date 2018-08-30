package com.lnlr.dynamic.pojo.master.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:39 2018/8/29
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    private Integer id;

    private String userName;


}
