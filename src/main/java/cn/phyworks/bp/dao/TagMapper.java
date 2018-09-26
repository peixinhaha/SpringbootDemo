package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.TagDo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author shouxueyun@163.com
 * @date 2018.09.08
 */
@Mapper
@Repository
public interface TagMapper {

    /**
     * 新增
     *
     * @param tagDo TagDo
     * @return 影响行数
     */
    @InsertProvider(type = TagMapperImpl.class, method = "save")
    @SelectKey(keyProperty = "id", before = false, statement = "select max(id) from tag", resultType = Long.class)
    Long save(TagDo tagDo);

    /**
     * 根据标签名查询单个
     *
     * @param name 标签名
     * @return 标签数据
     */
    @SelectProvider(type = TagMapperImpl.class, method = "getByName")
    TagDo getByName(String name);

    /**
     * 查询所有
     *
     * @return 列表
     */
    @SelectProvider(type = TagMapperImpl.class, method = "list")
    List<TagDo> list();

    /**
     * 根据文章查询所有标签
     *
     * @return 列表
     */
    @SelectProvider(type = TagMapperImpl.class, method = "listByIds")
    List<TagDo> listByIds(String articleId);

    /**
     * 根据 ID 查询单个
     *
     * @param id ID
     * @return 标签数据
     */
    @SelectProvider(type = TagMapperImpl.class, method = "get")
    TagDo get(Long id);

    /**
     * 停用
     *
     * @param id ID
     * @return 受影响的行数
     */
    @UpdateProvider(type = TagMapperImpl.class, method = "remove")
    Long remove(Long id);

    /**
     * 更新
     *
     * @param tagDo TagDo
     * @return 受影响的行数
     */
    @UpdateProvider(type = TagMapperImpl.class, method = "update")
    Long update(TagDo tagDo);

    /**
     * 获取最后插入的自增id
     *
     * @return 最大id
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "getLastInsertId")
    Long getLastInsertId();

    /**
     * 查询分页
     *
     * @param
     * @return 列表
     */
    @SelectProvider(type = TagMapperImpl.class, method = "listPagination")
    Page<TagDo> listPagination();
}
