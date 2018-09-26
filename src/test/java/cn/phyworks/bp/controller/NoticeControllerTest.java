package cn.phyworks.bp.controller;

import cn.phyworks.bp.dao.NoticeMapper;
import cn.phyworks.bp.pojo.bo.NoticeServiceSaveBo;
import cn.phyworks.bp.pojo.dto.NoticeServiceSaveDto;
import cn.phyworks.bp.service.NoticeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * NoticeControllerTest
 *
 * @author shouxueyun@163.com
 * @date 2018/9/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeMapper noticeMapper;

    private MockMvc mvc;
    private MockHttpSession session;

    private Long id;

    @Before
    public void beforeTest() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();

        NoticeServiceSaveDto noticeServiceSaveDto = new NoticeServiceSaveDto();
        noticeServiceSaveDto.setContent("测试");
        NoticeServiceSaveBo noticeServiceSaveBo = noticeService.save(noticeServiceSaveDto);

        id = noticeServiceSaveBo.getId();

    }

    @After
    public void afterTest() throws Exception {
    }


    @Test
    public void save() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/notices")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("content", "测试新增")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.content").value("测试新增"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void remove() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/notices/" + id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders.patch("/notices/" + id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("content", "测试修改")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.content").value("测试修改"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void list() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/notices")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload[0].content").value("测试"))
                .andDo(MockMvcResultHandlers.print());

    }
}
