package com.example.simpleMall.Controller;

import com.example.simpleMall.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Controller
public class FileController {

    @Autowired
    public DownloadService downloadService;

    @GetMapping(value="/download/files/{filePath}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(HttpSession session, @PathVariable("filePath") String filePath) throws FileNotFoundException {
        return downloadService.download("E:\\Downloads\\"+filePath);
    }
}
