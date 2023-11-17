package com.devdialogue.backend.config;

import com.devdialogue.backend.services.SecuredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecuredUserService securedUserService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securedUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/").hasAnyAuthority("USER_SELF_INFO", "ADMIN")
                .antMatchers(HttpMethod.POST,"/questions").hasAuthority("CREATE_QUESTIONS")
                .antMatchers(HttpMethod.GET, "/questions/**").hasAnyAuthority("VIEW_QUESTIONS", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/questions/").hasAnyAuthority("DELETE_QUESTIONS", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/questions/**").hasAnyAuthority("DELETE_SELF_QUESTIONS", "DELETE_QUESTIONS", "ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("CREATE_USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("VIEW_ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/").hasAnyAuthority("CREATE_ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }
}
