package com.yp.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class TomcatWebMain {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8080);
        tomcat.setConnector(connector);
        tomcat.start();
        tomcat.getServer().await();
    }
}
