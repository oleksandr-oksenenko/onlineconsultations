package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.UserDao;
import net.onlineconsultations.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class HibUserDaoImpl extends HibGenericDaoImpl<User> implements UserDao {
    public HibUserDaoImpl() {
        super(User.class);
    }

    public User findByUsername(String username) {
        return em.createQuery("select u " +
                " from User u " +
                " where u.username = :username",
                User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
