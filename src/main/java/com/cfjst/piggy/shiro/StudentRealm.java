package com.cfjst.piggy.shiro;

import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.service.StudentService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class StudentRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

        UserToken token = (UserToken)arg0;
        StudentService service = new StudentService();

        Student student = service.getById(token.getId());
        if(student==null){
            //用户不存在
            return null;
        }
        
        return new SimpleAuthenticationInfo(student,student.getPassword(),getName());
        

    }



}