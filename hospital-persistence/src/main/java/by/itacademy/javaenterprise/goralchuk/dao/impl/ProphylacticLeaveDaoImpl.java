package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.ProphylacticLeaveDao;
import by.itacademy.javaenterprise.goralchuk.entity.documents.ProphylacticLeave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProphylacticLeaveDaoImpl implements ProphylacticLeaveDao {
    private static final Logger logger = LoggerFactory.getLogger(ProphylacticLeaveDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ProphylacticLeave find(Long id) {
        ProphylacticLeave prophylacticLeave = entityManager.find(ProphylacticLeave.class, id);
        if (prophylacticLeave == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return prophylacticLeave;
        }
    }

    @Transactional
    @Override
    public ProphylacticLeave save(ProphylacticLeave prophylacticLeave) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(prophylacticLeave);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", prophylacticLeave);
            return prophylacticLeave;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public ProphylacticLeave update(ProphylacticLeave prophylacticLeave) {
        return null;
    }

    @Transactional
    @Override
    public long delete(Long id) {
        return 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProphylacticLeave> findAll() {
        return null;
    }
}
