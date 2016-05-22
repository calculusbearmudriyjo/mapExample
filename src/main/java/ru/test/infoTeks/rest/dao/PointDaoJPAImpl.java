package ru.test.infoTeks.rest.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.test.infoTeks.rest.dao.entities.PointEntity;

import javax.persistence.*;
import java.util.List;

public class PointDaoJPAImpl implements PointDao {
    @PersistenceContext(unitName = "restPersistence")
    private EntityManager entityManager;

    public List<PointEntity> getPoints() {
        String sqlString = "SELECT p FROM PointEntity p";
        TypedQuery<PointEntity> query = entityManager.createQuery(sqlString, PointEntity.class);
        return query.getResultList();
    }

    public PointEntity getPointById(Long id) {
        try {
            String sqlString = "SELECT p FROM PointEntity p WHERE p.id = ?1";
            TypedQuery<PointEntity> query = entityManager.createQuery(sqlString, PointEntity.class);
            query.setParameter(1, id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void deletePointById(Long id) {
        PointEntity point = entityManager.find(PointEntity.class, id);
        entityManager.remove(point);
    }

    @Transactional
    public Long createPoint(PointEntity point) {
        entityManager.merge(point);
        entityManager.flush();
        return point.getId();
    }

    @Transactional
    public void updatePoint(PointEntity point) {
        entityManager.merge(point);
    }

    @Transactional
    public void deleteAllPoints() {
        Query query = entityManager.createNativeQuery("TRUNCATE TABLE points");
        query.executeUpdate();
    }
}
