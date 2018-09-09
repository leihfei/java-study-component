package com.lnlr.authority.security.service;

import com.lnlr.authority.security.pojo.master.dto.DeptDTO;
import com.lnlr.authority.security.pojo.master.vo.DeptVO;
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
     * @param deptDTO
     * @return
     */
    Response create(DeptDTO deptDTO);

    /**
     * 更新部门
     * @param deptDTO
     * @return
     */
    Response update(DeptDTO deptDTO);

    /**
     * 删除部门
     * @param id
     * @return
     */
    Response delete(IdEntity id);
}
