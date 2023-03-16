package com.hillel.polezhaiev.projects.authentication_name_password.repo;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;
import com.hillel.polezhaiev.projects.authentication_name_password.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoInDatabase implements Repository{

    private final String url = "jdbc:mysql://127.0.0.1:3306/auth_name_password";
    private Connection connection;

    public RepoInDatabase(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Person person) throws UserPersistException {
        String sql = "INSERT INTO person (name, password)" +
                "VALUES(?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)){


            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsByUsername(String username) throws UserPersistException {
        String sql = "SELECT * FROM person";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            List<String> personList = new ArrayList<>();
            while(set.next()){
                String name = set.getString("name");
                personList.add(name);
            }

            return personList.contains(username);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkSizePassword(String password) throws PasswordLengthException {

        while(password.length() < 8){
            throw new PasswordLengthException(password);

        }
        return true;
    }
}
