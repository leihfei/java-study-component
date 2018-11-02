package com.lnlr.security.service;

import com.lnlr.security.pojo.master.dto.DeptParam;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.Response;

/**
 * @author:leihfei
 * @description 部门管理业务接口
 * @date:Create in 22:22 2018/9/3
 * @email:leihfein@gmail.com
 */
public interface SysDeptService {

    /**
     * 分页
     * @param ngPager
     * @return
     */
    NgData page(NgPager ngPager);
    /**
     * 创建部门
     * @param deptParam
     * @return
     */
    Response create(DeptParam deptParam);

    /**
     * 更新部门
     * @param deptParam
     * @return
     */
    Response update(DeptParam deptParam);

    /**
     * 删除部门
     * @param id
     * @return
     */
    Response delete(IdEntity id);
}
