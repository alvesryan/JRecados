package br.com.ryan.jrecados.model;

import java.util.UUID; // Biblioteca pra gerar ID

public class User {
    private String id;
    private String name;
    private String email;
    private String department;

    public User(String name, String email, String department){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
