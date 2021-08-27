package org.gaugekit.example.browser;

import com.thoughtworks.gauge.ClassInitializer;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Initializer implements ClassInitializer {

    private static final ApplicationContext applicationContext;

    static {
        applicationContext = new AnnotationConfigApplicationContext(App.class);
    }

    @Override
    public Object initialize(Class<?> aClass) {
        String[] beanNames = applicationContext.getBeanNamesForType(aClass);
        if (beanNames.length == 0) {
            throw new NoSuchBeanDefinitionException(aClass.getName());
        }
        String s = beanNames[0];
        return aClass.cast(applicationContext.getBean(s));
    }
}
