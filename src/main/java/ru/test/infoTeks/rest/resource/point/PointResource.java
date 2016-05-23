package ru.test.infoTeks.rest.resource.point;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.infoTeks.rest.dao.entities.PointEntity;
import ru.test.infoTeks.rest.error.AppException;
import ru.test.infoTeks.rest.resource.Point;
import ru.test.infoTeks.rest.service.PointServiceDbAccessImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.io.IOException;
import java.util.List;

@Component
@Path("/")
public class PointResource {
    private static final Logger logger = LoggerFactory.getLogger(PointResource.class);

    @Autowired
    private PointServiceDbAccessImpl pointService;

    @POST
    @Path("/point")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.TEXT_HTML})
    public Response createPoint(Point point) throws AppException {
        pointService.createPoint(point);
        return Response.status(Response.Status.CREATED)
                    .entity("A new point has benn created").build();
    }

    @DELETE
    @Path("/point")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.TEXT_HTML})
    public Response deletePoints() throws AppException {
        pointService.deleteAllPoints();
        return Response.status(Response.Status.NO_CONTENT)
                .entity("All point deleted").build();
    }

    @DELETE
    @Path("/point/{id}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.TEXT_HTML})
    public Response deletePoint(@PathParam("id") Long id) throws AppException {
        pointService.deletePointById(id);
        return Response.status(Response.Status.NO_CONTENT)
                .entity("point successfully deleted").build();
    }

    @GET
    @Path("/point")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Point> getPoint() throws IOException{
        List<Point> points = pointService.getPoints();
        return points;
    }

    @GET
    @Path("/point/{id}")
    public Viewable getPoint(@PathParam("id") Long id) throws AppException {
        Point point = pointService.getPointById(id);
        return new Viewable("/point.jsp", point);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable get() {
        return new Viewable("/main", this);
    }
}
