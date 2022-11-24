package com.javarush.quest.bulimov.questdelta.repository.hibernate;

import com.javarush.quest.bulimov.questdelta.entity.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class SessionCreator implements AutoCloseable{



    private final SessionFactory sessionFactory;

    public SessionCreator(Class clazz){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(clazz);
        sessionFactory = configuration.buildSessionFactory();
    }
    public SessionCreator(Class[] clazz){
        Configuration configuration = new Configuration();
        configuration.configure();
        Arrays.stream(clazz).forEach(configuration::addAnnotatedClass);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session open(Class clazz){
        SessionCreator creator = new SessionCreator(clazz);
        return creator.sessionFactory.openSession();
    }
    public static Session open(Class[] clazz){
        SessionCreator creator = new SessionCreator(clazz);
        return creator.sessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
