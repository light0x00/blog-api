package com.light.blog.web.test;

import com.light.blog.web.BlogApplication;
import com.light.blog.web.controller.msg.MsgCommentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MsgCommentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    MsgCommentController mcController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(mcController).build();
    }

    @Test
    public void query() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/mc/queryRoots")
                                .param("articleKey", "foo")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("response:" + result);
    }

}