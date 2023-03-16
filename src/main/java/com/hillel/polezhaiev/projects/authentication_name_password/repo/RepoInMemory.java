package com.hillel.polezhaiev.projects.authentication_name_password.repo;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoInMemory implements Repository{

    List<Person> persons = new ArrayList<>();

    @Override
    public void save(Person u) {
        persons.add(u);
    }

    @Override
    public boolean existsByUsername(String username) {
        return persons.stream()
                .filter(person -> person.getUsername().equals(username))
                .collect(Collectors.toList()).size() > 0;
    }

    @Override
    public boolean checkSizePassword(String password) throws PasswordLengthException {
        return false;
    }
}
