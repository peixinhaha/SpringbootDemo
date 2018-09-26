package cn.phyworks.bp.util;

import cn.phyworks.bp.exception.BusinessException;
import cn.phyworks.bp.support.ExceptionEnum;
import cn.phyworks.bp.support.Output;
import com.github.pagehelper.PageInfo;
import cn.phyworks.bp.support.Pagination;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Web 工具
 *
 * @author shyasiny@gmail.com
 * @date 2018.08.14
 */
@Slf4j
public class WebUtil {

    /**
     * 成功（无 Payload）
     */
    public static Output outputSuccess() {
        Output o = new Output();
        o.setCode(ExceptionEnum.SUCCESS.getCode());
        o.setMessage(ExceptionEnum.SUCCESS.getMessage());

        return o;
    }

    /**
     * 成功
     */
    @SuppressWarnings("unchecked")
    public static <T> Output outputSuccess(T payload) {
        Output o = new Output();
        o.setCode(ExceptionEnum.SUCCESS.getCode());
        o.setMessage(ExceptionEnum.SUCCESS.getMessage());
        o.setPayload(payload);

        return o;
    }

    /**
     * 成功
     */
    @SuppressWarnings("unchecked")
    public static <T> Output outputSuccess(T payload, T pagination) {
        Output o = new Output();
        o.setCode(ExceptionEnum.SUCCESS.getCode());
        o.setMessage(ExceptionEnum.SUCCESS.getMessage());
        o.setPayload(payload);
        o.setPagination(pagination);

        return o;
    }

    /**
     * 成功
     */
    @SuppressWarnings("unchecked")
    public static <T> Output outputSuccess(T payload, PageInfo pageInfo) {
        Pagination pagination = new Pagination();
        pagination.setTotal(pageInfo.getTotal());
        pagination.setTotalPage(pageInfo.getPages());
        pagination.setPage(pageInfo.getPageNum());
        pagination.setSize(pageInfo.getPageNum());

        Output o = new Output();
        o.setCode(ExceptionEnum.SUCCESS.getCode());
        o.setMessage(ExceptionEnum.SUCCESS.getMessage());
        o.setPayload(payload);
        o.setPagination(pagination);

        return o;
    }

    /**
     * 成功
     */
    @SuppressWarnings("unchecked")
    public static <T> Output outputSuccess(T payload, T pagination, T custom) {
        Output o = new Output();
        o.setCode(ExceptionEnum.SUCCESS.getCode());
        o.setMessage(ExceptionEnum.SUCCESS.getMessage());
        o.setPayload(payload);
        o.setPagination(pagination);
        o.setCustom(custom);

        return o;
    }

    /**
     * 错误
     */
    @SuppressWarnings("unchecked")
    public static Output outputError() {
        return new Output();
    }

    /**
     * 错误
     */
    @SuppressWarnings("unchecked")
    public static Output outputError(String code, String message) {
        Output o = new Output();
        o.setCode(code);
        o.setMessage(message);

        return o;
    }

    /**
     * 验证异常
     */
    @SuppressWarnings("unchecked")
    public static <T> Output outputValidationAbnormal(ConcurrentHashMap validation) {
        Output<T> o = new Output();
        o.setCode(ExceptionEnum.VALIDATION_ABNORMAL.getCode());
        o.setMessage(ExceptionEnum.VALIDATION_ABNORMAL.getMessage());
        o.setValidation((T)validation);

        return o;
    }

    /**
     * 抛出业务异常
     */
    public static void throwBusinessException(ExceptionEnum exceptionEnum) throws Exception {
        throw new BusinessException(exceptionEnum);
    }
}
