package com.github;

import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConnectionTimeoutDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConnectionTimeoutDemo.class, args);

        //EmbeddedServletContainerFactory
        TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) context.getBean(EmbeddedServletContainerFactory.class);
        TomcatEmbeddedServletContainer container = (TomcatEmbeddedServletContainer) factory.getEmbeddedServletContainer(servletContext -> {
        });

        AbstractProtocol protocolHandler = (AbstractProtocol) container.getTomcat().getConnector().getProtocolHandler();
        int expectedTimeout = 100500;
        int connectionTimeout = protocolHandler.getConnectionTimeout();
        if (connectionTimeout != expectedTimeout) {
            throw new IllegalStateException("incorrect connection timeout, expected [" + expectedTimeout + "] but found [" + connectionTimeout + "]");
        } else {
            System.out.println("Connection timeout is set as expected to " + expectedTimeout);
        }
    }
}
