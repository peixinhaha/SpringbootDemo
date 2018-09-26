package cn.phyworks.bp.exception;

import cn.phyworks.bp.support.ExceptionEnum;
import cn.phyworks.bp.support.Output;
import cn.phyworks.bp.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Jackson 格式化
 *
 * @author shyasiny@gmail.com
 * @date 2018.08.14
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 声明要捕获的异常
     * 方法执行完后返回的内容会返回到请求页面的 Body 上，直接显示在网页上
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Output defaultExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) {
        // 验证异常
        if (exception instanceof BindException) {

            Output<T> o = new Output();
            o.setCode(ExceptionEnum.VALIDATION_ABNORMAL.getCode());
            o.setMessage(ExceptionEnum.VALIDATION_ABNORMAL.getMessage());

            Map<String, String> validation = new ConcurrentHashMap<>(16);
            for (FieldError fe : ((BindException) exception).getFieldErrors()) {
                validation.put(fe.getField(), fe.getDefaultMessage());
            }
            o.setValidation((T)validation);

            return o;
        }

        // 业务异常
        if (exception instanceof BusinessException) {
            return WebUtil.outputError(((BusinessException) exception).getCustomCode(), ((BusinessException) exception).getCustomMessage());
        }

        for (StackTraceElement stackTraceElement: exception.getStackTrace()) {
            log.warn(stackTraceElement.toString());
        }
        log.warn(exception.getMessage());

        return WebUtil.outputError(ExceptionEnum.UNKNOWN.getCode(), exception.getMessage());
    }
}
