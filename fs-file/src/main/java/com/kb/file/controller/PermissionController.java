package com.kb.file.controller;

import com.kb.common.base.BaseResponse;
import com.kb.file.pojo.permission.Permission;
import com.kb.file.service.api.PermissionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 授权相关接口
 * @author syg
 * @version 1.0
 */
@RestController
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PostMapping("Permission")
    public BaseResponse addPermission(Permission permission){
        return permissionService.add(permission);
    }

    @PutMapping("Permission")
    public BaseResponse updatePermission(Permission permission){
        return permissionService.update(permission);
    }

    @DeleteMapping("Permission")
    public BaseResponse deletePermission(Integer[] ids){
        return permissionService.delete(ids);
    }

    @PostMapping("grantPermission")
    public BaseResponse addPermission(Integer roleId,Integer[] ids){
        return permissionService.addPermission(roleId,ids);
    }
}
