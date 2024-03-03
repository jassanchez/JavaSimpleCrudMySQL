package com.asanchez.crud.entity;

public class User {
    private Long user_id;
    private String name;
    private String lastname;

    public User() { }

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", name=" + name + ", lastname=" + lastname + "]";
    }
}
