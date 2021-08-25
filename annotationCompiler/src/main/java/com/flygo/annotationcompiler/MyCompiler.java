package com.flygo.annotationcompiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


//可以处理的注解
@SupportedAnnotationTypes("com.flygo.myannotation.Flygo")
public class MyCompiler extends AbstractProcessor {

    public MyCompiler(){
        super();
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (annotations.isEmpty()){
            Messager messager = processingEnv.getMessager();
            messager.printMessage(Diagnostic.Kind.NOTE,"---------------hello-------------");
        }

        return false;
    }
}
