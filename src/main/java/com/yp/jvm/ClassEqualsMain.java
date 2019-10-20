package com.yp.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * class对象的equals方法发不仅比较了两个class对象还比较了加载该class的classloader是否相等
 */
public class ClassEqualsMain {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch(IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Class classObj = myLoader.loadClass("com.yp.jvm.ClassEqualsMain");

        Object o = classObj.getConstructor().newInstance();

        System.out.println(o.getClass());
        System.out.println(ClassEqualsMain.class);
        System.out.println(o instanceof ClassEqualsMain);
    }
}
