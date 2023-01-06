package org.gecko.reformer.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 参考{@link org.gecko.reformer.processor.FeignFallbackProcessor}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.gecko.reformer.annotation.AutoFeignFallback")
public class FeignFallbackProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return true;
    }
}