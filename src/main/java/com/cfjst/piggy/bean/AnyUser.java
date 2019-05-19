package com.cfjst.piggy.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

//权限配置使用
public class AnyUser extends User {

	private static final long serialVersionUID = 3511090748888892639L;

	public AnyUser(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities) {
		super(name, password, authorities);
		this.id = id;
		this.name = name;
		this.password = password;
	}

	private Long id;
	private String name;
	private String password;

}