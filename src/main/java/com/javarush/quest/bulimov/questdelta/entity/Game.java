package com.javarush.quest.bulimov.questdelta.entity;


import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")


@Entity
public class Game extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_name")
    String userName;
    @Column(name = "current_question_id")
    Long currentQuestionId;
    @Column(name = "quest_id")
    Long questId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    GameStatus status;


    public Game() {

    }
}
