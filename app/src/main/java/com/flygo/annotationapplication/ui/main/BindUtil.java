package com.flygo.annotationapplication.ui.main;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class BindUtil {

    public static void bind(Fragment fragment){
        Class<? extends Fragment> fragmentClass = fragment.getClass();
        //获取当前类的所有方法，不包括父类
        Method[] declaredMethods = fragmentClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            //获取方法上所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == OnClick.class){
                    OnClick onClick = (OnClick) annotation;
                    int[] ids = onClick.value();
                    for (int id : ids) {
                        View view = fragment.getView().findViewById(id);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    method.invoke(fragment,view);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }else if (annotation.annotationType() == OnLongClick.class){
                    OnLongClick onClick = (OnLongClick) annotation;
                    int[] ids = onClick.value();
                    for (int id : ids) {
                        View view = fragment.getView().findViewById(id);
                        view.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                try {
                                    method.invoke(fragment,view);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                        });
                    }
                }
            }
        }
    }
}
