package github.fushaolei.wpserver;


import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class WpServerConfig {
//
//    @Bean
//    public TomcatServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers((Connector connector) -> {
//            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
//            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
//        });
//        return factory;
//    }
//
//}