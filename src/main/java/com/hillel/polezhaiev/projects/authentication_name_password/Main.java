package com.hillel.polezhaiev.projects.authentication_name_password;

import com.hillel.polezhaiev.projects.authentication_name_password.repo.RepoInDatabase;
import com.hillel.polezhaiev.projects.authentication_name_password.repo.Repository;
import com.hillel.polezhaiev.projects.authentication_name_password.service.SavePerson;
import com.hillel.polezhaiev.projects.authentication_name_password.service.Service;
import com.hillel.polezhaiev.projects.authentication_name_password.ui.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        final String username = System.getenv("MYSQL_USER");
        final String password = System.getenv("MYSQL_PASSWORD");

        Repository repository = new RepoInDatabase(username, password);
        SavePerson savePerson = new Service(repository);
        UserInterface userInterface = new UserInterface(savePerson);


    }


}
