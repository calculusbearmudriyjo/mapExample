package ru.test.infoTeks.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import ru.test.infoTeks.rest.filter.CORSResponseFilter;
import ru.test.infoTeks.rest.resource.point.PointResource;
import ru.test.infoTeks.rest.service.PointServiceDbAccessImpl;

public class MyApplication extends ResourceConfig {

    public MyApplication() {
        property(JspMvcFeature.TEMPLATES_BASE_PATH, "/WEB-INF/jsp");
        property(MvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/jsp");
        register(PointResource.class);

        register(RequestContextFilter.class);
        register(CORSResponseFilter.class);

        register(JacksonFeature.class);

        register(PointServiceDbAccessImpl.class);
        register(JacksonJsonProvider.class);
        register(MvcFeature.class);
        register(JspMvcFeature.class);
    }
}
