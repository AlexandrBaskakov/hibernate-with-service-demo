package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = query.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Employee employee = currentSession.get(Employee.class, id);

        // return the results
        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
