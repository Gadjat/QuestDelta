package com.javarush.quest.bulimov.questdelta.repository.dao;
import com.javarush.quest.bulimov.questdelta.entity.GameStatus;
import com.javarush.quest.bulimov.questdelta.exceptions.DaoException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class JdbcStarter {
    public static void main(String[] args) {

//        executeResult(SqlData.sqlGetGame);
//        executeQuery(SqlData.sqlGetGames);
//        prepExecute(SqlData.sqlTemplate);
        rowExecute(SqlData.sqlGetGames);
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

    private static void prepExecute(String template){
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(template);
        ){
            preparedStatement.setString(1, "sqlTest");
            preparedStatement.setLong(2, 45);
            preparedStatement.setLong(3, 3);
            preparedStatement.setInt(4, 1);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void rowExecute(String sql){
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ){

            ResultSet resultSet =  statement.executeQuery(sql);

            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            CachedRowSet cachedRowSet = rowSetFactory.createCachedRowSet();
            cachedRowSet.populate(resultSet);
            while(cachedRowSet.next()){
                System.out.println(cachedRowSet.getInt(1) + "\t" + cachedRowSet.getString(2) + "\t");
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
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
