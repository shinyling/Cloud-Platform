package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.constant.ErrorCodeEnum;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.FileBiz;
import com.everwing.cloud.service.platform.entity.File;
import com.everwing.cloud.service.platform.param.PagedParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 文件
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "文件", tags = "文件")
@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileBiz fileBiz;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    @SysLog("上传文件")
    public @ResponseBody
    ResultJson upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultJson.fail("文件是空");
        }
        return fileBiz.upload(file);
    }

    @ApiOperation("文件下载")
    @GetMapping("download/{id}")
    @SysLog("文件下载")
    public ResultJson download(@PathVariable String id, HttpServletResponse response) throws IOException {
        Assert.notNull(id, "参数错误!");
        ResultJson resultJson = fileBiz.download(id);
        if (resultJson.getCode().equals(ErrorCodeEnum.SUCCESS.getCode())) {
            Map<String, Object> resultMap = (Map<String, Object>) resultJson.getData();
            String fileName = (String) resultMap.get("fileName");
            byte[] bytes = (byte[]) resultMap.get("file");
            response.setContentType("application/x-download; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            ByteArrayInputStream byteArrayInputStream = null;
            BufferedInputStream bis = null;
            try {
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                bis = new BufferedInputStream(byteArrayInputStream);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return ResultJson.successWithMsg("文件下载成功!");
            } catch (IOException e) {
                log.error("文件下载失败!", e);
            } finally {
                byteArrayInputStream.close();
                bis.close();
            }
            return ResultJson.fail("文件下载失败!");
        }
        return resultJson;
    }

    @ApiOperation("删除文件")
    @DeleteMapping("delete/{id}")
    @SysLog("删除文件")
    public @ResponseBody
    ResultJson deleteById(@PathVariable String id) {
        Assert.notNull(id, "文件id不能为空");
        return fileBiz.deleteById(id);
    }

    @ApiOperation("查看文件列表")
    @PostMapping("loadPage")
    @SysLog("查看文件列表")
    public @ResponseBody
    ResultJson loadPage(@Validated @RequestBody PagedParam<File> pagedParam) {
        Assert.state(pagedParam.getPage().getCurrent() > 0, "分页参数错误!");
        Assert.state(pagedParam.getPage().getSize() > 0, "分页参数错误!");
        return fileBiz.loadPage(pagedParam);
    }
}
