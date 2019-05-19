package com.cfjst.piggy.config;



import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//用户权限配置
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled =  true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // 授权规则

        // http.authorizeRequests()
        // .antMatchers("/").permitAll()
        // .antMatchers("/login").permitAll()
        // .antMatchers("/student/**").hasRole("STUDENT")
        // .antMatchers("/teacher/**").hasRole("TEACHER")
        // .antMatchers("/admin/**").hasRole("ADMIN")
        // .and().rememberMe();
        // http.formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // // super.configure(auth);
        // auth.userDetailsService(userDetailsService());
        auth.inMemoryAuthentication()
        .withUser("1234").password("123456").roles("STUDENT")
        .and()
        .withUser("1234").password("123456").roles("TEACHER");
    }

    // @Override
    // protected UserDetailsService userDetailsService() {
    //     return new UserService();
    // }
    
    
}