package com.javarush.quest.bulimov.questdelta.util;

import com.javarush.quest.bulimov.questdelta.exceptions.WebAppException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

    private Property(){}
    public static String get(String field){
        try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){
            Properties property = new Properties();
            property.load(fis);
            return property.getProperty(field);
        } catch (RuntimeException | IOException e) {
            throw new WebAppException(e.getMessage());
        }
    }
}
