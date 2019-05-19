package com.cfjst.piggy.shiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 别问 我也不知道
 */
@Configuration
public class  ShiroConfig {
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
    // DefaultWebSecurityManagerDefaultWebSecurityManager
  
    
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截
        /**
         * anon 无需认证
         * authc   需认证
         * user remember 
         * perms 资源权限
         * role 角色权限
         */

         Map<String,String> filterMap = new LinkedHashMap<String,String>();
         
        filterMap.put("/student/**", "authc");
        filterMap.put("/**", "anon"); 

        //  filterMap.put("/user/del", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }
    
    


    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        //网上复制的
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


    @Bean
    public TeacherRealm getTeacherRealm(){
        
        return new TeacherRealm();
    }

    @Bean
    public StudentRealm getStudentRealm(){
        
        return new StudentRealm();
    }

    @Bean   
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
       
       
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(getStudentRealm());
        realms.add(getTeacherRealm());

        securityManager.setRealms(realms);;
        //配置Realm
        return securityManager;
    }


}

