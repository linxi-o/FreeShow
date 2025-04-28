package com.kb.file.service.impl;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.role.Role;
import com.kb.file.service.api.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author syg
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public BaseResponse addRoles(Role role) {
        return null;
    }

    @Override
    public BaseResponse update(Role role) {
        return null;
    }

    @Override
    public BaseResponse deleteRole(Integer[] ids) {
        return null;
    }

    @Override
    public BaseResponse addGrant(Integer roleId, Integer[] ids) {
        return null;
    }
}
