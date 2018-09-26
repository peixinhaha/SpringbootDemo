package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArticleTagDo;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleMapper interface
 *
 * @author peixin
 * @date 2018/09/11
 */
@Mapper
@Repository
public interface ArticleTagMapper {
    /**
     * 新增
     *
     * @param articleTagDo ArticleTagDo
     * @return 影响行数
     */
    @InsertProvider(type = ArticleTagMapperImpl.class, method = "save")
    Long save(ArticleTagDo articleTagDo);

    /**
     * 根据文章id查询
     *
     * @param articleId Long
     * @return 标签id
     */
    @SelectProvider(type = ArticleTagMapperImpl.class, method = "list")
    List<ArticleTagDo> list(Long articleId);

    /**
     * 根据文章id查询
     *
     * @param id Long
     * @return 标签id
     */
    @SelectProvider(type = ArticleTagMapperImpl.class, method = "listById")
    List<ArticleTagDo> listById(Long id);

    /**
     * 删除
     *
     * @param articleId Long, tagId Long
     * @return 影响行数
     */
    @DeleteProvider(type = ArticleTagMapperImpl.class, method = "remove")
    Long remove(Long articleId);
}
