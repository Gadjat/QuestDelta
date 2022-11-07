package com.javarush.quest.bulimov.questdelta.dao;

public class SqlData {
    private SqlData() {

    }

    public static final String sqlDeleteStatus = """
                                DROP TABLE IF EXISTS game.game_status;
                                """;

    public static final String sqlInsertStatus = """
            CREATE TABLE game.game_status(
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        status ENUM('PLAY', 'WIN', 'LOSE')
            )""";
    public static final String sqlGetGame = """
            SELECT g.id, user_name, g.current_question_id, quest_id, s.status FROM game.game g JOIN game.game_status s
            ON g.status_id = s.id
            """;
    public static final String sqlGetGames = """
            SELECT * FROM game.game limit 1
            """;
}
