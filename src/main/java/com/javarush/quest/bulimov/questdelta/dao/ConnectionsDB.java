package com.javarush.quest.bulimov.questdelta.dao;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class ConnectionsDB {
    private String DB_URL;
    private String DB_USER;
    private String DB_PASS;
}
