package com.luca.flavien.wineyardmanager.db.object;

import java.io.Serializable;

/**
 * Created by Flavien and Luca on 24.04.2017.
 *
 * Project : WineYardManager
 * Package: object
 *
 * Description: The Worker WineVariety for refelect the DB, we use in the code for recuperate informations
                We implements Serializable for use it in a intent (parameters)
 */

public class Worker implements Serializable {
    private int id;
    private String lastName;
    private String firstName;
    private String phone;
    private String mail ;


    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
