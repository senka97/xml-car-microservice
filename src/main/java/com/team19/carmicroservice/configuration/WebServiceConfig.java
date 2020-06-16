package com.team19.carmicroservice.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "comment")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema commentSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CommentPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.rent-a-car.com/car-service/soap");
        wsdl11Definition.setSchema(commentSchema);
        return wsdl11Definition;
    }

    @Bean(name = "statistic")
    public DefaultWsdl11Definition statisticWsdl11Definition(XsdSchema carStatistic) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StatPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.rent-a-car.com/car-service/soap");
        wsdl11Definition.setSchema(carStatistic);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema commentSchema() {
        return new SimpleXsdSchema(new ClassPathResource("comment.xsd"));
    }

    @Bean
    public XsdSchema carStatistic() {
        return new SimpleXsdSchema(new ClassPathResource("CarStatistic.xsd"));
    }

}
