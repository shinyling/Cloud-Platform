package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.PermissionFile;
import com.everwing.cloud.service.platform.service.IPermissionFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PermissionFileBiz {

    @Autowired
    private IPermissionFileService permissionFileService;

    public ResultJson add(PermissionFile permissionFile){
        QueryWrapper<PermissionFile> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionFile::getPermissionId,permissionFile.getPermissionId())
                .eq(PermissionFile::getFileId,permissionFile.getFileId());
        PermissionFile stored=permissionFileService.getOne(queryWrapper);
        if(stored!=null){
            return ResultJson.fail("权限文件绑定已存在!");
        }
        boolean flag=permissionFileService.save(permissionFile);
        if(flag){
            return ResultJson.successWithMsg("权限文件绑定成功!");
        }
        return ResultJson.fail("权限文件绑定失败!");
    }

    public ResultJson delete(PermissionFile permissionFile){
        QueryWrapper<PermissionFile> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionFile::getPermissionId,permissionFile.getPermissionId())
                .eq(PermissionFile::getFileId,permissionFile.getFileId());
        boolean flag=permissionFileService.remove(queryWrapper);
        if(flag){
            return ResultJson.successWithMsg("解除权限文件绑定关系成功!");
        }
        return ResultJson.fail("解除权限文件绑定关系失败!");
    }
}
