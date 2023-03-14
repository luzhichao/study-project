package org.gecko.reformer.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.constant.FlinkConstants;
import org.gecko.reformer.domain.SinkParamDO;
import org.gecko.reformer.handler.IHandler;

import java.util.Set;

/**
 * sink工具类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
public final class SinkUtils {
    private SinkUtils() {
    }

    /**
     * 设置sink属性数据
     *
     * @param jobName
     * @param handler
     * @param modelClazz
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static SinkParamDO setSinkProperties(String jobName, IHandler handler, Class<?> modelClazz) {
        final SinkParamDO result = new SinkParamDO();
        final Set<Class<?>> classes = ClassUtil.scanPackageBySuper(FlinkConstants.SCAN_BASE_PACKAGE_NAME, IHandler.class);
        for (Class<?> clazz : classes) {
            final boolean has = AnnotationUtil.hasAnnotation(clazz, FlinkHandler.class);
            if (has) {
                final FlinkHandler an = AnnotationUtil.getAnnotation(clazz, FlinkHandler.class);
                if (StrUtil.equals(jobName, an.jobName())) {
                    result.setHandler((IHandler) ReflectUtil.newInstance(clazz));
                    result.setModelClazz(an.modelClass());
                }
            }
        }
        return result;
    }
}
