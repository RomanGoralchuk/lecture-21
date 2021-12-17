package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.client.LifeStatus;
import by.itacademy.javaenterprise.goralchuk.entity.client.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient> {

    List<Patient> findAllPatientByLifeStatus(LifeStatus lifeStatus);

    long count();
}
