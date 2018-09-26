package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.NoticeDo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * NoticeMapper
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Mapper
@Repository
public interface NoticeMapper {

    /**
     * 新增
     *
     * @param noticeDo NoticeDo
     * @return 影响行数
     */
    @InsertProvider(type = NoticeMapperImpl.class, method = "save")
    @SelectKey(keyProperty = "id", before = false, statement = "select max(id) from notice", resultType = Long.class)
    Long save(NoticeDo noticeDo);

    /**
     * 获取最后插入的自增id
     *
     * @return 最大id
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "getLastInsertId")
    Long getLastInsertId();

    /**
     * 停用
     *
     * @param id ID
     * @return 受影响的行数
     */
    @UpdateProvider(type = NoticeMapperImpl.class, method = "remove")
    Long remove(Long id);

    /**
     * 根据 ID 查询单个
     *
     * @param id ID
     * @return 标签数据
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "get")
    NoticeDo get(Long id);

    /**
     * 更新
     *
     * @param noticeDo NoticeDo
     * @return 受影响的行数
     */
    @UpdateProvider(type = NoticeMapperImpl.class, method = "update")
    Long update(NoticeDo noticeDo);

    /**
     * 根据标签名查询单个
     *
     * @param content 标签名
     * @return 标签数据
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "getByContent")
    NoticeDo getByContent(String content);

    /**
     * 查询分页
     *
     * @param
     * @return 列表
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "listPagination")
    Page<NoticeDo> listPagination();
}
