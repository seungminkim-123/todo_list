package com.kshzzang44.todo.file.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileAPIController {
    @Value("${file.image.todo}") String todo_img_path;
    @Value("${file.image.member}") String member_img_path;

    @PutMapping("/{type}/upload")
    public ResponseEntity<Object> putImageUpload(
        @PathVariable String type, @RequestPart MultipartFile file
    ){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        System.out.println(file.getOriginalFilename());
        Path folderLocation = Paths.get(todo_img_path);
        Path targetFile = folderLocation.resolve(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
