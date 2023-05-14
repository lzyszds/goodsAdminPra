package com.example.demo005.domain;

public class UserId {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id=" + id +
                '}';
    }
}
