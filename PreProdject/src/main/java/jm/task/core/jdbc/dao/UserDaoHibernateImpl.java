package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(40), last_name VARCHAR(40), age INT, PRIMARY KEY(id))").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }

    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }
    }

    @Override
    public void saveUser(String name , String lastName , byte age) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
            session.getTransaction().commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            sessionFactory.getCurrentSession().beginTransaction().rollback();

        }
    }
}
