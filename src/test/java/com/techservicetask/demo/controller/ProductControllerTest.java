package com.techservicetask.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
//    @WithMockUser(roles = "{USER_ROLE}")
    public void showProducts() throws Exception{
//        mvc.perform(get("/product")).andExpect(status().isOk());

//
//        final MockHttpServletRequestBuilder registerUserRequest = get("/product")
//                    .with(user("admin").roles("ADMIN"))
//                    .contentType(MediaType.APPLICATION_JSON);
//
//          mvc.perform(registerUserRequest).andExpect(status().isOk());
    }

    @Test
    public void addProduct() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void removeProduct() {
    }

    @Test
    public void setQuantityValue() {
    }
}