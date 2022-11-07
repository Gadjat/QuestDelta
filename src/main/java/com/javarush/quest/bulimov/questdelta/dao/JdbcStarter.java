package com.javarush.quest.bulimov.questdelta.dao;
import com.javarush.quest.bulimov.questdelta.entity.GameStatus;
import com.javarush.quest.bulimov.questdelta.exceptions.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class JdbcStarter {
    public static void main(String[] args) {

        executeResult(SqlData.sqlGetGame);
        executeQuery(SqlData.sqlGetGames);

    }
    private static void execute(String sql){
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ){
            boolean execute = statement.execute(sql);
            if(execute) {
                throw new DaoException("incorrect operation sql " + sql);

            }
        }
        catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    private static void executeQuery(String sql){
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
                ) {
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();
            int metaDataColumn = metaData.getColumnCount();
            for(int i = 1; i <= metaDataColumn; i++){
                String name = metaData.getColumnName(i);
                String type = metaData.getColumnTypeName(i);

                System.out.println(name + "(" + type + ")");
            }

        }
        catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private static boolean executeUpdate(String sql){
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ){

            return statement.executeUpdate(sql) > 0;
        }
        catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }
    private static void executeResult(String sql){
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                String columnNane = metaData.getColumnName(i);
                System.out.printf("%-10s", columnNane);
            }
            System.out.println("\n" + "-".repeat(10*columnCount));
            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("user_name");
                long currentQuestionId = resultSet.getLong("current_question_id");
                long questId = resultSet.getLong("quest_id");
//                int status = resultSet.getInt("status_id");
                GameStatus status = GameStatus.valueOf(resultSet.getString("status"));
                System.out.printf("\n"+"%-10s %-10s %-10s %-10s %-10s", id, name, currentQuestionId, questId, status.toString());


            }

        }
        catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        ConnectionsDB connect =  ConnectionPool.getConnect();
        try {
            return DriverManager.getConnection(
                    connect.getDB_URL(),
                    connect.getDB_USER(),
                    connect.getDB_PASS()
            );
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

}
