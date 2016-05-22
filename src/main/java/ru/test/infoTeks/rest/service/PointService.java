package ru.test.infoTeks.rest.service;


import ru.test.infoTeks.rest.dao.entities.PointEntity;
import ru.test.infoTeks.rest.error.AppException;
import ru.test.infoTeks.rest.resource.Point;

import java.util.List;

public interface PointService {
    public List<Point> getPoints();

    public Point getPointById(Long id) throws AppException;

    public void deletePointById(Long id) throws AppException;

    public Long createPoint(PointEntity point) throws AppException;

    public void updatePoint(PointEntity point) throws AppException;

    public void deleteAllPoints();
}
