package cn.phyworks.bp.web;

import cn.phyworks.bp.dao.TypeMapper;
import cn.phyworks.bp.support.Output;
import cn.phyworks.bp.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类控制器
 *
 * @author shouxueyun@163.com
 * @date 2018.09.10
 */
@Slf4j
@RestController
@CrossOrigin
public class TypeController {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 列表
     */
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public Output list() {
        return WebUtil.outputSuccess(typeMapper.list());
    }

}
