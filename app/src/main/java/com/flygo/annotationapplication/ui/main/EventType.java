package com.flygo.annotationapplication.ui.main;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 这个注解是作用在自定义注解上的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventType {

    Class listenerType();

    String listenerSetter();

}
