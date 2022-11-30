package com.javarush.quest.bulimov.questdelta.repository.hibernate;







import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.Arrays;

public class SessionCreator implements AutoCloseable{



    private final SessionFactory sessionFactory;

    @SneakyThrows
    public SessionCreator(Class clazz){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(clazz);
        sessionFactory = configuration.buildSessionFactory();
    }

    @SneakyThrows
    public static Session open(Class clazz)  {
        SessionCreator creator = new SessionCreator(clazz);
        return creator.sessionFactory.openSession();
    }


    @SneakyThrows
    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
