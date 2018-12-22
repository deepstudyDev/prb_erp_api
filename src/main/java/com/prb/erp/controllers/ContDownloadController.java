package com.prb.erp.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chequer.axboot.core.controllers.BaseController;

@Controller
@RequestMapping(value = "/api/v1/contFile")
public class ContDownloadController extends BaseController {
 

    @Value("${axboot.upload.cont.repository}")
    public String uploadRepository;
    
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam String custCd) throws IOException {
    	if (custCd == null)
            return null;

    	String fileName = custCd+".pdf";
        byte[] bytes = FileUtils.readFileToByteArray(new File(getSavePath(fileName)));

        //String c = EncodeUtils.encodeDownloadFileName(commonFile.getFileNm());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public String getBasePath() {
        return uploadRepository;
    }

    public String getSavePath(String saveName) {
        return getBasePath() + "/" + saveName;
    }
}
