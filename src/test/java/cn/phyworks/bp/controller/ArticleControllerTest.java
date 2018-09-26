package cn.phyworks.bp.controller;

import cn.phyworks.bp.dao.ArticleMapper;
import cn.phyworks.bp.dao.NoticeMapper;
import cn.phyworks.bp.pojo.bo.ArticleServiceSaveBo;
import cn.phyworks.bp.pojo.dto.ArticleServiceSaveDto;
import cn.phyworks.bp.service.ArticleService;
import cn.phyworks.bp.web.ArticleContoller;
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
 * ArticleControllerTest class
 *
 * @author peixin
 * @date 2018/09/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ArticleControllerTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContoller articleContoller;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private NoticeMapper noticeMapper;

    private MockMvc mvc;
    private MockHttpSession session;

    private Long id;


    @Before
    public void beforeTest() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();

        ArticleServiceSaveDto articleServiceSaveDto = new ArticleServiceSaveDto();
        articleServiceSaveDto.setContent("我是内容");
        articleServiceSaveDto.setIntroduction("我是简介");
        articleServiceSaveDto.setMonth("09");
        articleServiceSaveDto.setYear(2018);
        articleServiceSaveDto.setTitle("我是标题");
        articleServiceSaveDto.setTypeId(Long.valueOf(2));
        articleServiceSaveDto.setTypeName("后端");
        articleServiceSaveDto.setTagIds("1,2");
        ArticleServiceSaveBo articleServiceSaveBo = articleService.save(articleServiceSaveDto);

        id = articleServiceSaveBo.getId();
    }

    @Test
    public void save() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/articles")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .param("content", "测试我是内容")
                        .param("title", "测试我是标题")
                        .param("introduction", "测试我是简介")
                        .param("typeId", "1")
                        .param("year", "2018")
                        .param("month", "09")
                        .param("typeName", "前端")
                        .param("tagIds", "1,2,3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.content").value("测试我是内容"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.title").value("测试我是标题"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.introduction").value("测试我是简介"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.type_name").value("前端"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void remove() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/articles/" + id)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.patch("/articles/" + id)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .param("content", "测试我是内容1")
                        .param("title", "测试我是标题1")
                        .param("introduction", "测试我是简介1")
                        .param("typeId", "3")
                        .param("typeName", "工具")
                        .param("tagIds", "1,2,3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.content").value("测试我是内容1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.title").value("测试我是标题1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.introduction").value("测试我是简介1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.type_name").value("工具"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void get() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/articles/" + id)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listPagination() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/articles/all/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listPaginationByType() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/articles/type/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listPaginationByTag() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/articles/tag/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listPaginationByTime() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/articles/time/201809")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }
}
