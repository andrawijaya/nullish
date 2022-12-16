package com.enigma.controller;

import com.enigma.Service.ICourseUploadService;
import com.enigma.model.request.FormDataWithFile;
import com.enigma.model.response.ErrorResponse;
import com.enigma.model.response.SuccessResponse;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/course-upload")
public class CourseUploadController {
    private ICourseUploadService courseUploadService;

    public CourseUploadController(ICourseUploadService courseUploadService) {
        this.courseUploadService = courseUploadService;
    }

    @PostMapping
    public ResponseEntity upload(FormDataWithFile formDataWithFile) {
            MultipartFile file = formDataWithFile.getFile();
            courseUploadService.uploadMaterial(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Upload Store", file.getOriginalFilename()));
    }

    @GetMapping
    public ResponseEntity download(@RequestParam String fileName){
        Resource file = courseUploadService.downloadMaterial(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/multi-files")
    public ResponseEntity uploadMultiFile( MultipartFile[] files){
        try{
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file ->{
                courseUploadService.uploadMaterial(file);
                fileNames.add(file.getOriginalFilename());
            });
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Upload Store", fileNames));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ErrorResponse("00", "Error je"));
        }
    }
}
