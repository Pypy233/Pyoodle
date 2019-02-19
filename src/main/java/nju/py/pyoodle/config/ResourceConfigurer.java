package nju.py.pyoodle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: py
 * @Date: 2019/2/19 下午3:51
 * @Version 1.0
 */

@Configuration
public class ResourceConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/J2EEStrorage/**").addResourceLocations("file:///Users/py/J2EEStrorage/");
    }
}

