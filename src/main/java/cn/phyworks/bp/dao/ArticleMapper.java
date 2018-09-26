package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArticleDo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleMapper class
 *
 * @author peixin
 * @date 2018/09/10
 */
@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 新增
     *
     * @param articleDo ArticleDo
     * @return 影响行数
     */
    @InsertProvider(type = ArticleMapperImpl.class, method = "save")
    @SelectKey(keyProperty = "id", before = false, statement = "select max(id) from article", resultType = Long.class)
    Long save(ArticleDo articleDo);

    /**
     * 删除
     *
     * @param id Long
     * @return 影响行数
     */
    @UpdateProvider(type = ArticleMapperImpl.class, method = "remove")
    Long remove(Long id);

    /**
     * 修改
     *
     * @param articleDo ArticleDo
     * @return 影响行数
     */
    @UpdateProvider(type = ArticleMapperImpl.class, method = "update")
    Long update(ArticleDo articleDo);

    /**
     * 根据id查询
     *
     * @param id Long
     * @return 文章
     */
    @SelectProvider(type = ArticleMapperImpl.class, method = "getById")
    ArticleDo getById(Long id);

    /**
     * 查询分页
     *
     * @param
     * @return 文章列表
     */
    @SelectProvider(type = ArticleMapperImpl.class, method = "listPagination")
    List<ArticleDo> listPagination();

    /**
     * 根据分类查询分页
     *
     * @param id Long
     * @return 文章列表
     */
    @SelectProvider(type = ArticleMapperImpl.class, method = "listPaginationByType")
    List<ArticleDo> listPaginationByType(Long id);

    /**
     * 根据ids查询分页
     *
     * @param tagId String
     * @return 文章列表
     */
    @SelectProvider(type = ArticleMapperImpl.class, method = "listPaginationByTag")
    List<ArticleDo> listPaginationByTag(String tagId);

    /**
     * 根据日期查询分页
     *
     * @param year Interge month String
     * @return 文章列表
     */
    @SelectProvider(type = ArticleMapperImpl.class, method = "listPaginationByTime")
    List<ArticleDo> listPaginationByTime(Integer year, String month);
}
