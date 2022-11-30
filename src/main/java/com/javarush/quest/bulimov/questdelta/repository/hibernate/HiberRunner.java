package com.javarush.quest.bulimov.questdelta.repository.hibernate;

import com.javarush.quest.bulimov.questdelta.entity.Game;

import com.javarush.quest.bulimov.questdelta.entity.GameStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class HiberRunner {



    public void get(Class clazz, Long id){

    }
    public void create(Class clazz){
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(employee);
//            transaction.commit();
//        }
    }
    public void update(Class clazz, Long id){

    }
    public void delete(Class clazz, Long id){

    }


    public static void main(String[] args) {
        try(Session session = SessionCreator.open(Game.class)){
            Transaction transaction = session.beginTransaction();
            Game gameCreate = Game.with()
                    .userName("new")
                    .currentQuestionId(29L)
                    .questId(63L)
                    .status(GameStatus.PLAY)
                    .build();
            session.save(gameCreate);
            transaction.commit();



            Game game = session.find(Game.class, 1L);
            System.out.println(game.toString());


            Query<Game> query = session.createQuery("from Game", Game.class);
            query.stream().forEach(System.out::println);
        }
//        Class[] classes = new Class[]{Game.class, GameStatus.class};
//        try(Session session = SessionCreator.open(classes)) {
//            Transaction transaction = session.beginTransaction();
//            Query<Game> query = session.createQuery("from Game where status.id = 1", Game.class);
//            query.stream().forEach(s-> System.out.println(s.toString()));
//        }


//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(Game.class);
//        Session session = configuration.buildSessionFactory().openSession();

    }
}
