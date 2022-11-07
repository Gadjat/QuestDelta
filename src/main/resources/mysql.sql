DROP TABLE IF EXISTS game;

CREATE TABLE game (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      user_name VARCHAR(100) DEFAULT NULL,
                      current_question_id BIGINT DEFAULT NULL,
                      quest_id BIGINT DEFAULT NULL,
                      status_id INT DEFAULT NULL
);

DROP TABLE IF EXISTS game_status;

CREATE TABLE game_status(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            status ENUM('PLAY', 'WIN', 'LOSE')
);