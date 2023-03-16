package com.hillel.polezhaiev.projects.authentication_name_password.service;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.ExistsByUsernameException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;
import com.hillel.polezhaiev.projects.authentication_name_password.model.Person;
import com.hillel.polezhaiev.projects.authentication_name_password.repo.Repository;

public class Service implements SavePerson{

    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void savePerson(String username, String password) throws ExistsByUsernameException, UserPersistException, PasswordLengthException {
        Person person = new Person(username, password);

        if(repository.existsByUsername(username)){
            throw new ExistsByUsernameException(username);
        }

        repository.checkSizePassword(password);
        repository.save(person);

    }
}
