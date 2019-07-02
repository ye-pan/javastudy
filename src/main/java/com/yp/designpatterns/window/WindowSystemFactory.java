package com.yp.designpatterns.window;

public class WindowSystemFactory {

    public WindowImpl createWindowImpl() {
        return null;
    }

    public static WindowSystemFactory getInstance() {
        return WindowSystemFactoryHolder.INSTANCE;
    }

    private static class WindowSystemFactoryHolder {
        static final WindowSystemFactory INSTANCE = new WindowSystemFactory() {
            @Override
            public WindowImpl createWindowImpl() {
                return new XWindowImpl();
            }
        };
    }
}
