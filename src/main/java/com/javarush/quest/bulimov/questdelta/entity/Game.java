package com.javarush.quest.bulimov.questdelta.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "getAll", query = "from Game")
})

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
    @Enumerated(EnumType.STRING)
    GameStatus status;


    public Game() {

    }
}
