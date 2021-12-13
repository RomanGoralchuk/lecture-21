package by.itacademy.javaenterprise.goralchuk.dao.impl;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDao;
import by.itacademy.javaenterprise.goralchuk.entity.client.LifeStatus;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.documents.SickLeave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {
    private static final Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Patient find(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient == null) {
            logger.debug("Object not found");
            return null;
        } else {
            logger.debug("Operation completed");
            return patient;
        }
    }

    @Transactional
    @Override
    public Patient save(Patient patient) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(patient);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", patient);
            return patient;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("Transaction failed {}", e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public Patient update(Patient patient) {
        try {
            entityManager.getTransaction().begin();
            entityManager.refresh(patient);
            entityManager.getTransaction().commit();
            logger.debug("The transaction was successful - {}", patient.getPatientIdCardNumber());
            return patient;
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
            entityManager.getTransaction().begin();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<Patient> criteriaDelete = cb.createCriteriaDelete(Patient.class);
            Root<Patient> root = criteriaDelete.from(Patient.class);

            criteriaDelete.where(cb.equal(root.get("patientIdCardNumber"), id));

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
    public List<Patient> findAll() {
        try {
            List<Patient> patientList = entityManager.createQuery("FROM Patient").getResultList();
            return patientList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Patient> findAllPatientByLifeStatus(LifeStatus lifeStatus) {
        try {
            List<Patient> patientList = entityManager.createQuery("from Patient where lifeStatus = :lifeStatus")
                    .setParameter("lifeStatus", lifeStatus)
                    .getResultList();
            return patientList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public long count() {
        return entityManager
                .createQuery("select count(e) from Patient e", Long.class)
                .getSingleResult();
    }
}
