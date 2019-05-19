package com.cfjst.piggy.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.DefaultSecurityManager;
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
        shiroFilterFactoryBean.setLoginUrl("/logi2n");
        
        return shiroFilterFactoryBean;
    }
	

    @Bean
    public UserRealm getRealm(){
        
        return new UserRealm();
    }

    @Bean   
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
       
        //配置Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


}

