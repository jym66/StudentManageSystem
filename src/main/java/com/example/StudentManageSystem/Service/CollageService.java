package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.CollegeDao;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.college;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollageService {
    @Autowired
    CollegeDao collageDao;

    public Response getCollage(){
        return Response.success(collageDao.findAll());
    }

    public Response addCollage(String collage){
        if (getCollege(collage)!= null){
            return Response.fail("学院已存在！");
        }
        collageDao.save(new college(collage));
        return Response.success("添加成功");
    }
    private college getCollege(String college){
//        验证是否存在
        return collageDao.findByCollegeName(college);
    }
}
