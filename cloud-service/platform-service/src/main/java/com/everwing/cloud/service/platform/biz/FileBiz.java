package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.File;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IFileService;
import com.everwing.cloud.service.platform.utils.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DELL shiny
 * @date 2020/3/18
 */
@Slf4j
@Component
public class FileBiz {

    @Autowired
    private IFileService fileService;

    @Autowired
    private FastDFSClient fastDFSClient;

    public ResultJson upload(MultipartFile file) {
        try {
            String uri = fastDFSClient.uploadFile(file);
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            File dataFile = new File();
            dataFile.setName(fileName);
            dataFile.setSize(file.getSize());
            dataFile.setSuffix(suffixName);
            dataFile.setUrl(uri);
            dataFile.setIsDelete(false);
            boolean flag = fileService.save(dataFile);
            if (flag) {
                return ResultJson.successWithMsg("上传文件成功!");
            }
        } catch (IOException e) {
            log.error("上传文件失败!", e);
            throw new RuntimeException("上传文件失败!");
        }
        return ResultJson.fail("上传文件失败!");
    }

    public ResultJson download(String id) {
        File file = fileService.getById(id);
        if (file != null) {
            Map<String, Object> resultMap = new HashMap<>(2);
            resultMap.put("fileName", file.getName());
            String fileUrl = file.getUrl();
            if (StringUtils.isNotBlank(fileUrl)) {
                byte[] bytes = fastDFSClient.download(fileUrl);
                resultMap.put("file", bytes);
                return ResultJson.success(resultMap);
            }
        }
        return ResultJson.fail("下载文件失败!");
    }

    public ResultJson deleteById(String id) {
        File file = fileService.getById(id);
        if (file != null) {
            String fileUrl = file.getUrl();
            if (StringUtils.isNotBlank(fileUrl)) {
                fastDFSClient.deleteFile(fileUrl);
                fileService.removeById(id);
            }
            return ResultJson.successWithMsg("文件删除成功!");
        }
        return ResultJson.fail("文件删除失败!");
    }

    public ResultJson loadPage(PagedParam<File> pagedParam) {
        Page page = pagedParam.getPage();
        return ResultJson.success(fileService.page(page));
    }
}
