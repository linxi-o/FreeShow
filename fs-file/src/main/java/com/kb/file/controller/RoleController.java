package com.kb.file.controller;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.role.Role;
import com.kb.file.service.api.RoleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author syg
 * @version 1.0
 */
@RestController
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("addRole")
    public  BaseResponse addRoles(Role role){
        return roleService.addRoles(role);
    }

    @PutMapping("updateRole")
    public BaseResponse updateRole(Role role){
        return roleService.update(role);
    }

    @DeleteMapping("deleteRole")
    public BaseResponse deleteRole(Integer[] ids){
        return roleService.deleteRole(ids);
    }

    @PostMapping("grant")
    public BaseResponse addGrant(Integer roleId,Integer[] ids){
        return roleService.addGrant(roleId,ids);
    }
}
