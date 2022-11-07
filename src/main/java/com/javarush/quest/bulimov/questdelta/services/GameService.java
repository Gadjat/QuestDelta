package com.javarush.quest.bulimov.questdelta.services;

import com.javarush.quest.bulimov.questdelta.entity.Game;
import com.javarush.quest.bulimov.questdelta.entity.GameStatus;
import com.javarush.quest.bulimov.questdelta.repository.GameRepository;
import com.javarush.quest.bulimov.questdelta.repository.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

public enum GameService {

    INSTANCE;
    private final Repository<Game> gameRepository = GameRepository.get();


    public Collection<Game> getAll(){
        return gameRepository.getAll()
                .toList();
    }

    public Game get(Long id){
        return gameRepository.get(id);
    }


    public void update(Game game) {
        gameRepository.update(game);
    }

    public Long create(String name) {
        Game game = Game.with()
                .questId(1L)
                .currentQuestionId(1L)
                .userName(name)
                .status(GameStatus.PLAY)
                .build();
        return gameRepository.create(game);
    }

    public void delete(Game game){
        gameRepository.delete(game);
    }
}
