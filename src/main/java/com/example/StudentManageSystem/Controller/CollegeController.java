package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.CollageService;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollegeController {
    @Autowired
    CollageService collageService;
    @RequestMapping(value = "/getCollage",method = RequestMethod.POST)
    public Response getCollage(){
        return collageService.getCollage();
    }
}
