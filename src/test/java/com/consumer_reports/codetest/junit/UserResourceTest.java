package com.consumer_reports.codetest.junit;

import com.consumer_reports.codetest.controller.UserResource;
import com.consumer_reports.codetest.core.ApiError;
import com.consumer_reports.codetest.daos.AddressRepository;
import com.consumer_reports.codetest.daos.UserRepository;
import com.consumer_reports.codetest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserResource.class)
public class UserResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private ApiError apiError;

    @MockBean
    private UserService userService;

    @Test // Test-1: getAllUser (/users/) API
    public void shouldReturnValidResponseOnGetAllUser() throws Exception {
        mockMvc.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("response")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test // Test-2: getAllUser (/users/) API
    public void shouldReturnValidResponseOnGetUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("_links")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test // Test-2: getAllUser (/users/) API
    public void shouldReturnValidResponseOnDeleteProfileById() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());
    }
}
