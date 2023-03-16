package com.hillel.polezhaiev.projects.authentication_name_password.service;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.ExistsByUsernameException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;

public interface SavePerson {
    void savePerson(String username, String password) throws ExistsByUsernameException, UserPersistException, PasswordLengthException;
}
