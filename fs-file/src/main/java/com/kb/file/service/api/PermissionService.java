package com.kb.file.service.api;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.permission.Permission;

/**
 * @author syg
 * @version 1.0
 */
public interface PermissionService {
    /**
     * 添加
     * @param permission
     * @return
     */
    BaseResponse add(Permission permission);

    /**
     * 更新
     * @param permission
     * @return
     */
    BaseResponse update(Permission permission);

    /**
     * 删除
     * @param ids
     * @return
     */
    BaseResponse delete(Integer[] ids);

    /**
     * 授权
     * @param roleId
     * @param ids
     * @return
     */
    BaseResponse addPermission(Integer roleId, Integer[] ids);
}
