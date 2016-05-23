package ru.test.infoTeks.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.test.infoTeks.rest.dao.PointDao;
import ru.test.infoTeks.rest.dao.entities.PointEntity;
import ru.test.infoTeks.rest.error.AppException;
import ru.test.infoTeks.rest.filter.AppConstant;
import ru.test.infoTeks.rest.resource.Point;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PointServiceDbAccessImpl implements PointService {

    private static final Logger logger = LoggerFactory.getLogger(PointServiceDbAccessImpl.class);

    @Autowired
    private PointDao pointDao;

    public List<Point> getPoints() {
        List<PointEntity> points = pointDao.getPoints();
        List<Point> response = new ArrayList<Point>(points.size());
        for(PointEntity point  : points) {
            response.add(new Point(point));
        }
        return response;
    }

    public Point getPointById(Long id) throws AppException {
        if(id == null) {
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "please set search id", null, AppConstant.URL);
        }
        PointEntity point = pointDao.getPointById(id);
        return new Point(point);
    }

    public void deletePointById(Long id) throws AppException {
        if(id == null) {
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "please set delete id", null, AppConstant.URL);
        }
        pointDao.deletePointById(id);
    }

    public Long createPoint(PointEntity point) throws AppException{
        if(point == null) {
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "please set property point", null, AppConstant.URL);
        }
        Long id = pointDao.createPoint(point);
        return id;
    }

    public Long createPoint(Point point) throws AppException{
        if(point == null) {
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "please set property point", null, AppConstant.URL);
        }
        PointEntity pointEntity = new PointEntity(point.getTitle(),point.getLongitude(),point.getLatitude());
        Long id = createPoint(pointEntity);
        return id;
    }


    public void updatePoint(PointEntity point) throws AppException{
        if(point == null) {
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "please set property point", null, AppConstant.URL);
        }
        pointDao.updatePoint(point);
    }

    public void deleteAllPoints() {
        pointDao.deleteAllPoints();
    }
}
