package com.kb.file.service.impl;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.permission.Permission;
import com.kb.file.service.api.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author syg
 * @version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public BaseResponse add(Permission permission) {
        return null;
    }

    @Override
    public BaseResponse update(Permission permission) {
        return null;
    }

    @Override
    public BaseResponse delete(Integer[] ids) {
        return null;
    }

    @Override
    public BaseResponse addPermission(Integer roleId, Integer[] ids) {
        return null;
    }
}
