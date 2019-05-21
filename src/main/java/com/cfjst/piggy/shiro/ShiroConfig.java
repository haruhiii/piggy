package com.cfjst.piggy.shiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
         
        filterMap.put("/student/**", "user");
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
    public CookieRememberMeManager cookieRememberMeManager(){

        ///配置记住我功能的cookie
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200000);
        cookieRememberMeManager.setCookie(simpleCookie);
        ///这一行必须要自己编码，不然每次登录都会报错一次
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));

        return cookieRememberMeManager;
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
        securityManager.setRememberMeManager(cookieRememberMeManager());
        //配置Realm
        return securityManager;
    }


    @Bean
    public Realm getTeacherRealm(){
        
        return new TeacherRealm();
    }

    @Bean
    public Realm getStudentRealm(){
        
        return new StudentRealm();
    }
}

