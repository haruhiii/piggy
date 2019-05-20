package com.cfjst.piggy.shiro;

import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.service.TeacherService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class TeacherRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {



        UserToken token = (UserToken)arg0;
        TeacherService service = new TeacherService();

        Teacher teacher = service.getById(token.getId());
        if(teacher==null){
            //用户不存在
            return null;
        }

        return new SimpleAuthenticationInfo(teacher,teacher.getPassword(),getName());
    }



}