package cn.phyworks.bp.web;

import cn.phyworks.bp.dao.ArrangementMapper;
import cn.phyworks.bp.support.Output;
import cn.phyworks.bp.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：peixin
 * @ Date       ：Created in 2018/9/9 下午9:10
 * @ Description：ArrangementController
 * @ Modified By：
 * @Version: 0.0.1
 */
@RestController
@Slf4j
@CrossOrigin
public class ArrangementController {
    @Autowired
    private ArrangementMapper arrangementMapper;

    /**
     * 列表
     */
    @RequestMapping(value = "/arrangements", method = RequestMethod.GET)
    public Output list() {
        return WebUtil.outputSuccess(arrangementMapper.list());
    }
}
