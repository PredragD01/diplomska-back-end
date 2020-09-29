package com.mailautomation.mailautomation.dtos;

public class Manager_info {
    int id;
    String name;
    String surname;

    public Manager_info(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manager_info() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
