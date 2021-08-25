package com.flygo.annotationapplication.ui.main;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BindUtil2 {

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
                        Object listener = Proxy.newProxyInstance(fragment.getClass().getClassLoader(),
                                new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
                                        return method.invoke(fragment,args);
                                    }
                                });

                        view.setOnClickListener((View.OnClickListener) listener);
                    }

                }else if (annotation.annotationType() == OnLongClick.class){
                    OnLongClick onClick = (OnLongClick) annotation;
                    int[] ids = onClick.value();
                    for (int id : ids) {
                        View view = fragment.getView().findViewById(id);
                        Object listener = Proxy.newProxyInstance(fragment.getClass().getClassLoader(),
                                new Class[]{View.OnLongClickListener.class}, new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
                                        return method.invoke(fragment,args);
                                    }
                                });

                        view.setOnLongClickListener((View.OnLongClickListener) listener);
                    }
                }
            }
        }
    }
}
