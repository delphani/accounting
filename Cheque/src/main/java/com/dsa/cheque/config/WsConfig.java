package com.dsa.cheque.config;


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

@Configuration
@EnableWs
public class WsConfig extends WsConfigurerAdapter {
    public static final String CHEQUE_SERVICE_SCHEMA_NAMESPACE = "https://api.middleeastbank.net/cheque/chequeService";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "returnChequeInfo")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema chequeInfoSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("returnChequeInfoPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(CHEQUE_SERVICE_SCHEMA_NAMESPACE);
        wsdl11Definition.setSchema(chequeInfoSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema chequeInfoSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/chequeservice.xsd"));
    }
}
