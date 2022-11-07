package com.javarush.quest.bulimov.questdelta.dao;

import com.javarush.quest.bulimov.questdelta.exceptions.DaoException;
import com.javarush.quest.bulimov.questdelta.util.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

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
