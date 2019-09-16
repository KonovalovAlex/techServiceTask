package com.techservicetask.demo.configs;

import com.techservicetask.demo.filter.FilterToken;
import com.techservicetask.demo.jwtAuthentication.JwtAuthenticationEntryPoint;
import com.techservicetask.demo.service.JwtUserService;
import com.techservicetask.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true, jsr250Enabled = true)
@PropertySource(value={"classpath:application.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserService jwtUserService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenUtil jwtTokenUtil, JwtUserService jwtUserService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserService = jwtUserService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/registrate","/login","/h2").permitAll()
                .antMatchers("/product/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        FilterToken authenticationFilterToken = new FilterToken(userDetailsService(), jwtTokenUtil, tokenHeader);
        httpSecurity
                .addFilterBefore(authenticationFilterToken, UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath
            )
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/**/*.html"
            );
    }

    @Autowired
    public void setUnauthorizedHandler(JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setJwtUserService(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }
}
