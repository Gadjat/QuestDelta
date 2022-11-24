package com.javarush.quest.bulimov.questdelta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public enum GameStatus {
    PLAY, WIN, LOSE;
}
