package com.lnlr.security.pojo.master.dto;

import com.lnlr.security.pojo.master.entity.SysAcl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author:leihfei
 * @description: 权限点传输对象
 * @date:Create in 1:17 2018/11/1
 * @email:leihfein@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AclDTO extends SysAcl {

    /**
     * 是否要默认选中
     */
    private boolean checked = false;

    /**
     * 是否有权限操作
     */
    private boolean hasAcl = false;

    public static AclDTO adapt(SysAcl acl) {
        AclDTO dto = new AclDTO();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }
}
