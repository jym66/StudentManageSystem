package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.CollegeDao;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollageService {
    @Autowired
    CollegeDao collageDao;

    public Response getCollage(){
        return Response.success(collageDao.findAll());
    }
}
