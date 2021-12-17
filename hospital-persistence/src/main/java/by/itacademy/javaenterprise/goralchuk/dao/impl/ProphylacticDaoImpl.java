package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.ProphylacticDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.Prophylactic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProphylacticDaoImpl implements ProphylacticDao {
    private static final Logger logger = LoggerFactory.getLogger(ProphylacticDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Prophylactic find(Long id) {
        Prophylactic prophylactic = entityManager.find(Prophylactic.class, id);
        if (prophylactic == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return prophylactic;
        }
    }

    @Transactional
    @Override
    public Prophylactic save(Prophylactic prophylactic) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(prophylactic);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", prophylactic);
            return prophylactic;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public Prophylactic update(Prophylactic patient) {
        return null;
    }

    @Transactional
    @Override
    public long delete(Long id) {
        return 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Prophylactic> findAll() {
        return null;
    }
}
