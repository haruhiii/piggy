    package com.cfjst.piggy.config;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@EnableAuthorizationServer
public class AuthorizationConfig implements AuthorizationServerConfigurer {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


        clients.inMemory()
        .withClient("clientId")
        .secret("secret")
        .authorizedGrantTypes("authorizedGrantTypes")
        .scopes("app")
        .redirectUris("http://www.baidu.com");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    }

    } 