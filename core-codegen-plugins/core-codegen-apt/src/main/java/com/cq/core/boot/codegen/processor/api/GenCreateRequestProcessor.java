package com.cq.core.boot.codegen.processor.api;

import com.cq.core.boot.codegen.processor.BaseCodeGenProcessor;
import com.cq.core.boot.codegen.processor.DefaultNameContext;
import com.cq.core.boot.codegen.processor.creator.IgnoreCreator;
import com.cq.core.boot.codegen.spi.CodeGenProcessor;
import com.cq.core.boot.commons.model.Request;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: Gim
 * @Date: 2019/11/28 19:33
 * @Description:
 */

@AutoService(value = CodeGenProcessor.class)
public class GenCreateRequestProcessor extends BaseCodeGenProcessor {

    public static final String CREATE_REQUEST_SUFFIX = "CreateRequest";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        DefaultNameContext nameContext = getNameContext(typeElement);
        Set<VariableElement> fields = findFields(typeElement,
                p -> Objects.isNull(p.getAnnotation(IgnoreCreator.class)));
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(nameContext.getCreateClassName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Request.class)
                .addAnnotation(Schema.class);
        addSetterAndGetterMethodWithConverter(typeSpecBuilder, fields);
        genJavaSourceFile(generatePackage(typeElement),
                typeElement.getAnnotation(GenCreateRequest.class).sourcePath(), typeSpecBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenCreateRequest.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(GenCreateRequest.class).pkgName();
    }
}