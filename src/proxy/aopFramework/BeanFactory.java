package proxy.aopFramework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by john on 2016/11/28.
 */
public class BeanFactory {
    Properties properties = new Properties();

    public BeanFactory(InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) throws Exception {
        String clazzName = properties.getProperty(name);
        Object bean;
        Class<?> clazz = Class.forName(clazzName);
        bean = clazz.newInstance();
        if (bean instanceof ProxyFactoryBean) {
            ProxyFactoryBean proxyFactorybean = (ProxyFactoryBean) bean;
            proxyFactorybean.setTarget(Class.forName(properties.getProperty(name + ".target")).newInstance());
            proxyFactorybean.setAdvice((Advice) Class.forName(properties.getProperty(name + ".advice")).newInstance());
            return ((ProxyFactoryBean) bean).getProxy();
        }
        return bean;
    }
}
