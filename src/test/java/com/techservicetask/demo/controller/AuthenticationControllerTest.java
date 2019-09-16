package com.techservicetask.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservicetask.demo.entity.Role;
import com.techservicetask.demo.entity.User;
import com.techservicetask.demo.jwtAuthentication.JwtAuthenticationRequest;
import com.techservicetask.demo.jwtAuthentication.JwtUser;
import com.techservicetask.demo.service.JwtUserService;
import com.techservicetask.demo.service.impl.JwtUserServiceImpl;
import com.techservicetask.demo.util.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtUserServiceImpl jwtUserService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }



    @Test
    @WithMockUser(roles = "USER")
    public void successfulRefreshTokenWithUserRole() throws Exception {

        Role role = new Role();
        role.setId(0L);
        role.setRole("ROLE_USER");

        User user = new User();
        user.setUsername("username");
        user.setRole(role);
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis() + 1000 * 1000));

        User jwtUser = jwtUserService.generateJwtUser(user);

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(user.getUsername());
        when(jwtUserService.loadUserByUsername(eq(user.getUsername()))).thenReturn((UserDetails) jwtUser);
        when(jwtTokenUtil.canTokenBeRefreshed(any(), any())).thenReturn(true);

    }
    @Test
    @WithAnonymousUser
    public void shouldGetUnauthorizedWithAnonymousUser() throws Exception {

        mvc.perform(get("/refresh"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void createAuthenticationToken()throws Exception {
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest("admin", "admin");

        mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(jwtAuthenticationRequest)))
                .andExpect(status().is2xxSuccessful());
    }

}