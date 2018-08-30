package com.lnlr.dynamic.pojo.slave.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:40 2018/8/29
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "test1")
@Data
public class TestSecond {

    @Id
    private Integer id;

    private String testName;
}
