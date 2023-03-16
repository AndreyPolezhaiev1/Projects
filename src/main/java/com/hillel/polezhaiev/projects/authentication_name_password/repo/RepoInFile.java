package com.hillel.polezhaiev.projects.authentication_name_password.repo;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;
import com.hillel.polezhaiev.projects.authentication_name_password.model.Person;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RepoInFile implements Repository{
    @Override
    public void save(Person u) throws UserPersistException {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream("users.txt", true);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.write(String.format("Name: %s;\tpassword: %s;\n", u.getUsername(), u.getPassword()));

            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            throw new UserPersistException(String.format("Unable to persist user %s ", u.getUsername()), e);
        }

    }

    @Override
    public boolean existsByUsername(String username) throws UserPersistException {

        try(FileInputStream fileInputStream = new FileInputStream("users.txt")){
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String string = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
            String [] newString = string.split(";");

            return Arrays.stream(newString).anyMatch(name -> (name.substring(name.indexOf(' ') + 1)).equals(username));

        } catch(IOException e){
            throw new UserPersistException(String.format("Unable to persist user %s ", username), e);

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
