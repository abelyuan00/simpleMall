package com.example.simpleMall.Controller;

import com.example.simpleMall.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@RestController
public class FileController {


    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public DownloadService downloadService;

    @GetMapping(value="/download/filePath")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(HttpSession session, @RequestParam("path") String filePath) throws Exception {
        return downloadService.download(filePath);
    }
}
