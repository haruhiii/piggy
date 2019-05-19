package com.cfjst.piggy.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserToken extends UsernamePasswordToken {
    
    private static final long serialVersionUID = -9062657584489319876L;
    private String type;
    private Long id;

    public UserToken(final Long id,final String password,final String type){
        super(String.valueOf(id),password);
        this.type=type;
        this.id = id;
    }
}