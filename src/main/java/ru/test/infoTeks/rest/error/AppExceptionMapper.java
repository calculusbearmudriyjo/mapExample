package ru.test.infoTeks.rest.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {
    public Response toResponse(AppException e) {
        return Response.status(e.getStatus())
                .entity(new ErrorMessage(e))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
