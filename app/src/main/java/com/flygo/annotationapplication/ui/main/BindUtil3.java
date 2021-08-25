package com.flygo.annotationapplication.ui.main;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BindUtil3 {

    public static void bind(Fragment fragment){
        Class<? extends Fragment> fragmentClass = fragment.getClass();
        //获取当前类的所有方法，不包括父类
        Method[] declaredMethods = fragmentClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            //获取方法上所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //获取注解的类型，比如OnClick.Class
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //判断注解上是否有EventType注解
                if (annotationType.isAnnotationPresent(EventType.class)){
                    //获取EventType注解
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    Class listenerType = eventType.listenerType();
                    String listenerSetter = eventType.listenerSetter();
                    try {
                        Method value = annotationType.getDeclaredMethod("value");
                        int [] viewIds = (int[]) value.invoke(annotation);
                        //使用动态代理，获取到代理的接口实例对象
                        Object proxy = Proxy.newProxyInstance(fragment.getClass().getClassLoader(), new Class[]{listenerType}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
                                return method.invoke(fragment,args);
                            }
                        });
                        for (int viewId : viewIds) {
                            View view = fragment.getView().findViewById(viewId);
                            //反射找到要代理的方法
                            //获取所有public的Method（包括父类）
                            Method setterMethod = view.getClass().getMethod(listenerSetter, listenerType);
                            setterMethod.invoke(view,proxy);
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
