package cn.redandelion.seeha.core.sys.basic.configuration;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class DynamicConfigurationBaseDto {

    @Autowired
    private ApplicationContext applicationContext;
    @Bean
    public Runnable  ConfigurationBaseDto() throws  Exception{
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)applicationContext;
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(ServiceRequest.class);
        /*
        * 设置属性
        *
        * */
//        beanDefinitionBuilder.addPropertyValue("userId",10002L);
        beanDefinitionBuilder.addPropertyValue("locale","ZH_CN");
//        beanDefinitionBuilder.addPropertyValue("roleId",10002L);

        listableBeanFactory.registerBeanDefinition("iRequestHelper",beanDefinitionBuilder.getBeanDefinition());
        return null;
    }


}
