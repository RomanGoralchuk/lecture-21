package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.SickLeaveDao;
import by.itacademy.javaenterprise.goralchuk.entity.documents.SickLeave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

@Repository
public class SickLeaveDaoImpl implements SickLeaveDao {
    private static final Logger logger = LoggerFactory.getLogger(SickLeaveDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public SickLeave find(Long id) {
        SickLeave sickLeave = entityManager.find(SickLeave.class, id);
        if (sickLeave == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return sickLeave;
        }
    }

    @Transactional
    @Override
    public SickLeave save(SickLeave sickLeave) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sickLeave);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", sickLeave);
            return sickLeave;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public SickLeave update(SickLeave sickLeave) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaUpdate<SickLeave> criteriaUpdate = cb.createCriteriaUpdate(SickLeave.class);
            Root<SickLeave> root = criteriaUpdate.from(SickLeave.class);

            criteriaUpdate.where(cb.equal(root.get("numberSickLeave"), sickLeave.getNumberSickLeave()));

            entityManager.getTransaction().begin();
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();
            return sickLeave;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public long delete(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<SickLeave> criteriaDelete = cb.createCriteriaDelete(SickLeave.class);
            Root<SickLeave> root = criteriaDelete.from(SickLeave.class);

            criteriaDelete.where(cb.equal(root.get("numberSickLeave"), id));

            entityManager.getTransaction().begin();
            entityManager.createQuery(criteriaDelete).executeUpdate();
            entityManager.getTransaction().commit();
            return id;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return 0;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<SickLeave> findAll() {
        try {
            List<SickLeave> list = entityManager.createQuery("from SickLeave").getResultList();
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
