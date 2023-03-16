package com.hillel.polezhaiev.projects.authentication_name_password.exceptions;

public class ExistsByUsernameException extends Exception{

    public ExistsByUsernameException(String message){
        super(message);
    }
}
