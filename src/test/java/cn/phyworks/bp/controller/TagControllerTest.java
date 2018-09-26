package cn.phyworks.bp.controller;

import cn.phyworks.bp.dao.TagMapper;
import cn.phyworks.bp.pojo.bo.TagServiceSaveBo;
import cn.phyworks.bp.pojo.dto.TagServiceSaveDto;
import cn.phyworks.bp.service.TagService;
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
 * TagControllerTest
 *
 * @author shouxueyun@163.com
 * @date 2018/9/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TagControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    private MockMvc mvc;
    private MockHttpSession session;

    private Long id;

    @Before
    public void beforeTest() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();

        TagServiceSaveDto tagServiceSaveDto = new TagServiceSaveDto();
        tagServiceSaveDto.setName("测试");
        TagServiceSaveBo tagServiceSaveBo = tagService.save(tagServiceSaveDto);

        id = tagServiceSaveBo.getId();

    }

    @Test
    public void save() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/tags")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", "测试新增")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.name").value("测试新增"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void remove() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/tags/" + id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {

        mvc.perform(MockMvcRequestBuilders.patch("/tags/" + id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", "测试修改")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.name").value("测试修改"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void list() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/tags")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());

    }
}
