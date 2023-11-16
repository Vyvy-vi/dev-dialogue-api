package com.devdialogue.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("vyom")
                .password("$2a$10$NcWl7r7R7IWZSRaheNpbpu4junHJC3CWRx3wxaY4gsUJQzFAGKBy.")
                .authorities("user")
                .and()
                .withUser("jain")
                .password("$2a$10$s6XCE/hMlDpKyH3/Wqg0Xel/9qA1oPVR.nxsxxuVPSluE4hX3sf..")
                .authorities("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasAuthority("user")
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPE() {
        return new BCryptPasswordEncoder();
    }
}
