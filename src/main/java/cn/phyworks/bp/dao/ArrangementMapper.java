package cn.phyworks.bp.dao;

import cn.phyworks.bp.domain.ArrangementDo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArrangementMapper
 *
 * @author peiixn
 * @date 2018.09.09
 */
@Mapper
@Repository
public interface ArrangementMapper {

    /**
     * 新增
     *
     * @param arrangementDo ArrangementDo
     * @return 影响行数
     */
    @InsertProvider(type = ArrangementMapperImpl.class, method = "save")
    Long save(ArrangementDo arrangementDo);

    /**
     * 更新
     *
     * @param id Long
     * @return 受影响的行数
     */
    @UpdateProvider(type = ArrangementMapperImpl.class, method = "update")
    Long update(Long id, String type);

    /**
     * 查询所有
     *
     * @return 用户列表
     */
    @SelectProvider(type = ArrangementMapperImpl.class, method = "list")
    List<ArrangementDo> list();

    /**
     * 根据姓名查询单个
     *
     * @param year, month
     * @return 用户数据
     */
    @SelectProvider(type = ArrangementMapperImpl.class, method = "getByYearAndMonth")
    ArrangementDo getByYearAndMonth(Integer year, String month);
}
