package com.hillel.polezhaiev.projects.authentication_name_password.ui;

import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.ExistsByUsernameException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.PasswordLengthException;
import com.hillel.polezhaiev.projects.authentication_name_password.exceptions.UserPersistException;
import com.hillel.polezhaiev.projects.authentication_name_password.service.SavePerson;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    private SavePerson savePerson;
    private JTextField usernameInput = new JTextField();
    private JLabel usernameLabel = new JLabel("username");

    private JTextField passwordInput = new JPasswordField();
    private JLabel passwordLAbel = new JLabel("password");

    private JButton button = new JButton("save");
    private JLabel buttonLabel = new JLabel();

    {
        setSize(600, 400);
        setLayout(new GridLayout(3, 2));
        setVisible(true);

        add(usernameLabel);
        add(usernameInput);

        add(passwordLAbel);
        add(passwordInput);

        add(buttonLabel);
        add(button);

    }

    public UserInterface(SavePerson savePerson) throws HeadlessException {
        this.savePerson = savePerson;

        button.addActionListener(e ->{

            try{
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                savePerson.savePerson(username, password);
                buttonLabel.setForeground(Color.BLUE);
                buttonLabel.setText("Successfully saved");

            } catch (ExistsByUsernameException ex){
                System.out.printf("User %s already exists\n", ex.getMessage());
                buttonLabel.setForeground(Color.RED);
                buttonLabel.setText(String.format("User %s already exists\n", ex.getMessage()));
            } catch (UserPersistException ex){
                System.out.printf("Unable to persist user: %s; cause: %s\n", ex.getMessage(), ex.getCause());
                buttonLabel.setForeground(Color.RED);
                buttonLabel.setText(String.format("Unable to store User:\n", ex.getMessage()));
            } catch (PasswordLengthException ex){
                System.out.printf("Unable to store password: %s; cause: smaller than 8 symbols\n", ex.getMessage());
                buttonLabel.setForeground(Color.RED);
                buttonLabel.setText(String.format("Password must be bigger than 8 symbols:\n", ex.getMessage()));
            }
        });

    }
}
