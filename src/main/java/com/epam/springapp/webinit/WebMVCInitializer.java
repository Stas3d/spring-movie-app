package com.epam.springapp.webinit;

import com.epam.springapp.App;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //  java-based configurations:

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { App.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { App.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
