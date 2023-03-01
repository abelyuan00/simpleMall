package com.example.simpleMall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/3/2022, Monday
 **/
@Controller
public class UploadController {


    @Resource
    private StandardServletMultipartResolver standardServletMultipartResolver;

    private final static String FILE_UPLOAD_PATH = "C:\\AllWork\\Project\\simpleMall\\src\\main\\resources\\static\\dist\\img";
    //Pic mapping need to use path with prefix "file:"


    @GetMapping({"/upload/singleFile"})
    public String uploadSingleFile() {
        return "upload";
    }


    @RequestMapping(value = "/upload/singleFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        if (file.isEmpty()) {
            return "Upload Failed";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //file name generator
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String customerID =  session.getAttribute("customerId") != null? session.getAttribute("customerId").toString():null ;
        String newFileName = "customer_" + customerID +"_"+tempName.toString();
        if(null!=session.getAttribute("customerId")) {
            newFileName = "customer " + session.getAttribute("customerId").toString() + newFileName;
        }
        try {
            // saving file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
            Files.write(path, bytes);
            System.out.println("[File uploaded], file path = D:/file/upload/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[File uploaded], file path = D:/file/upload/" + newFileName;
    }


//    //deal with multiple file upload with same names
//    @RequestMapping(value = "/upload/filesBySameName", method = RequestMethod.POST)
//    @ResponseBody
//    //multiple file would use requestPart other than requestParam
//    //param should have the same name as html input tag name <input type="file" name="files"/><br><br>
//    public String uploadFilesBySameName(@RequestPart MultipartFile[] files) {
//        if (files == null || files.length == 0) {
//            return "Empty file or file not selected";
//        }
//        if (files.length > 5) {
//            return "No more than 5 files";
//        }
//        String uploadResult = "Uploaded，path : <br>";
////        Arrays.stream(files).forEach(file->{});
//        for (MultipartFile file : files) {
//            String fileName = file.getOriginalFilename();
//            if (StringUtils.isEmpty(fileName)) {
//                //no file, break loop
//                continue;
//            }
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            //file name generator
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//            Random r = new Random();
//            StringBuilder tempName = new StringBuilder();
//            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
//            String newFileName = tempName.toString();
//            try {
//                // save file
//                byte[] bytes = file.getBytes();
//                Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
//                Files.write(path, bytes);
//                uploadResult += "/upload/" + newFileName + "<br>";
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return uploadResult;
//    }


//    @RequestMapping(value = "/upload/filesByDifferentName", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadFilesByDifferentName(HttpServletRequest httpServletRequest) {
//        List<MultipartFile> multipartFiles = new ArrayList<>(8);
//        // only deal with file upload
//        if (!standardServletMultipartResolver.isMultipart(httpServletRequest)) {
//            return "No loadable file selected";
//        }
//        // parsing HttpServletRequest object to MultipartHttpServletRequest object
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
//        Iterator<String> iter = multiRequest.getFileNames();
//        int total = 0;
//        while (iter.hasNext()) {
//            if (total > 5) {
//                return "No more than 5 files";
//            }
//            total += 1;
//            MultipartFile file = multiRequest.getFile(iter.next());
//            multipartFiles.add(file);
//        }
//        if (CollectionUtils.isEmpty(multipartFiles)) {
//            return "No file selected";
//        }
//        if (multipartFiles != null && multipartFiles.size() > 5) {
//            return "No more than 5 files";
//        }
//        String uploadResult = "Uploaded，path : <br>";
//        for (int i = 0; i < multipartFiles.size(); i++) {
//            String fileName = multipartFiles.get(i).getOriginalFilename();
//            if (StringUtils.isEmpty(fileName)) {
//                //No files, break loop
//                continue;
//            }
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            //name generator
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//            Random r = new Random();
//            StringBuilder tempName = new StringBuilder();
//            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
//            String newFileName = tempName.toString();
//            try {
//                // save files
//                byte[] bytes = multipartFiles.get(i).getBytes();
//                Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
//                Files.write(path, bytes);
//                uploadResult += "/upload/" + newFileName + "<br>";
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return uploadResult;
//    }
}
