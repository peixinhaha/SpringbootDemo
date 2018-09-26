package cn.phyworks.bp.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * PackageControllerTest controller 测试打包
 *
 * @author shouxueyun@163.com
 * @date 2018/9/11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrangementControllerTest.class,
        NoticeControllerTest.class,
        TagControllerTest.class,
        TypeControllerTest.class,
        ArticleControllerTest.class
})
public class PackageControllerTest {
}
