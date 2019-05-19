package com.cfjst.piggy.service;

import java.util.ArrayList;
import java.util.List;

import com.cfjst.piggy.bean.AnyUser;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 权限服务
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Student student = studentService.getById(Long.parseLong(id));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (student != null){
            //学生权限添加
            authorities.add(new SimpleGrantedAuthority("STUDENT"));
            return new AnyUser(student.getId(), student.getName(), student.getPassword(), authorities);
        }else{
            Teacher teacher = teacherService.getById(Long.parseLong(id));
            if (teacher != null){
                //教师权限添加
                authorities.add(new SimpleGrantedAuthority("TEACHER"));
                return new AnyUser(teacher.getId(), teacher.getName(), teacher.getPassword(), authorities);
            }

            // TODO 管理员权限添加

        }
        throw new UsernameNotFoundException("用户不存在");

    }

    
}