package com.javarush.quest.bulimov.questdelta.repository.dao;

import com.javarush.quest.bulimov.questdelta.util.Property;

import java.util.HashSet;

public class ConnectionPool {
    private static HashSet<ConnectionsDB> connectionPool = new HashSet<>();
    private ConnectionPool(){}


    public static ConnectionsDB getConnect(){
        if(connectionPool.isEmpty()){
            init();
        }
        return connectionPool.stream().findFirst().get();
    }

    public static ConnectionsDB init()  {
        ConnectionsDB connect =  ConnectionsDB.with()
                .DB_URL(Property.get("db.host"))
                .DB_USER(Property.get("db.login"))
                .DB_PASS(Property.get("db.password"))
                .build();
        connectionPool.add(connect);
        return connect;
    }


}
