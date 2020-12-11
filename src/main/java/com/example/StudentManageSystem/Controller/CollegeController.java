package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.CollageService;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class CollegeController {
    @Autowired
    CollageService collageService;

    @RequestMapping(value = "/getCollage",method = RequestMethod.POST)
//    获取学院
    public Response getCollage(){
        return collageService.getCollage();
    }

    @RequestMapping(value = "/addCollage",method = RequestMethod.POST)
//    添加学院
    public Response addCollage(@NotBlank(message = "学院不能为空") String collage){
        return collageService.addCollage(collage);
    }
}
