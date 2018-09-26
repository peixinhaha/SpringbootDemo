package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.TypeDo;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TypeMapper
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Mapper
@Repository
public interface TypeMapper {

    /**
     * 查询所有
     *
     * @return 列表
     */
    @SelectProvider(type = TypeMapperImpl.class, method = "list")
    List<TypeDo> list();

    /**
     * 根据 ID 查询单个
     *
     * @param id ID
     * @return 分类数据
     */
    @SelectProvider(type = TypeMapperImpl.class, method = "get")
    TypeDo get(Long id);

    /**
     * 更新
     *
     * @param typeDo TypeDo
     * @return 受影响的行数
     */
    @UpdateProvider(type = TypeMapperImpl.class, method = "update")
    Long update(TypeDo typeDo);

    /**
     * 更新使用数量
     *
     * @return 受影响的行数
     */
    @UpdateProvider(type = TypeMapperImpl.class, method = "updateUsedTotal")
    Long updateUsedTotal(Long id, String type);

    /**
     * 获取最后插入的自增id
     *
     * @return 最大id
     */
    @SelectProvider(type = NoticeMapperImpl.class, method = "getLastInsertId")
    Long getLastInsertId();

}
