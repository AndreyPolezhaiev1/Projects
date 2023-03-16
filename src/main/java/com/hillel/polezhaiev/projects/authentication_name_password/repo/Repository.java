package com.hillel.polezhaiev.projects.authentication_name_password.repo;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;
import com.hillel.polezhaiev.projects.authentication_name_password.model.Person;

import java.sql.SQLException;

public interface Repository {
    void save(Person u) throws UserPersistException;
    boolean existsByUsername(String username) throws UserPersistException;
    boolean checkSizePassword(String password) throws PasswordLengthException;
}
