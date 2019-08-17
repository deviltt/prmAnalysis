package com.tt.oa.controller;

import com.tt.BaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ZTreeControllerTest extends BaseTest {
    @Test
    public void testZTree() throws Exception {
        ZTreeController zTreeController = new ZTreeController();
        MockMvc mockMvc = standaloneSetup(zTreeController).build();
        mockMvc.perform(get("/ztree/toZTree")).andExpect(view().name("ztree"));
    }
}
