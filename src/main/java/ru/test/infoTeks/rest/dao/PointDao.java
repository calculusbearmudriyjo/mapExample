package ru.test.infoTeks.rest.dao;

import ru.test.infoTeks.rest.dao.entities.PointEntity;

import java.util.List;

public interface PointDao {
    public List<PointEntity> getPoints();

    public PointEntity getPointById(Long id);

    public void deletePointById(Long id);

    public Long createPoint(PointEntity point);

    public void updatePoint(PointEntity point);

    public void deleteAllPoints();
}
