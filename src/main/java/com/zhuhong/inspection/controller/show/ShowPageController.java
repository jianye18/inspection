package com.zhuhong.inspection.controller.show;

import com.alibaba.excel.util.FileUtils;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.service.AnnexService;
import com.zhuhong.inspection.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 展示页controller
 * @Author: jian.ye
 * @Date: 2019/10/17 17:46
 */
@Api(value = "展示页controller")
@Slf4j
@RestController
@RequestMapping("/api/show/")
public class ShowPageController extends BaseController {

    @Autowired
    private AnnexService annexService;

    @Value("${upload_path}")
    private String fileDir;

    /**
     * 下载附件文件
     * @Author: jian.ye
     * @Date: 2019/11/4 15:12
     */
    @ApiOperation(value = "下载附件文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务数据ID"),
            @ApiImplicitParam(name = "baseType", value = "业务数据类型")
    })
    @GetMapping("downloadFile")
    public ResponseEntity<byte[]> downloadFile(Integer businessId, Integer baseType) {
        String LOG_MSG = "下载附件文件---downloadFile()---，";
        log.debug("下载文件的参数：{businessId=" + businessId + ",baseType=" + baseType + "}");
        List<Annex> list = annexService.getAnnexList(businessId, baseType);
        int l = list.size();
        if (l > 0) {
            try {
                String fileName = "";
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                String path = fileDir + "docs\\";
                if (l == 1) {
                    fileName = list.get(0).getName();
                } else {
                    fileName = System.currentTimeMillis() + ".zip";
                    List<String> filePaths = new ArrayList<>();
                    list.forEach(annex -> filePaths.add(annex.getPath()));
                    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path + fileName));
                    FileUtil.zipFile(filePaths, zos);
                }
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
                File file = new File(path + fileName);
                byte[] b = FileUtils.readFileToByteArray(file);
                if (l > 1) {
                    FileUtil.delAllFile(path + fileName);
                }
                return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
            }  catch (Exception e) {
                e.printStackTrace();
                log.error(LOG_MSG + "异常信息，", e);
                return null;
            }
        } else {
            return null;
        }
    }

}
