package br.com.ryan.jrecados.model;

import java.util.Objects;
import java.util.UUID; // Biblioteca pra gerar ID

public class User {
    private final String id;
    private String name;
    private String email;
    private String department;
    private boolean ativo;

    public User(String name, String email, String department){
        this.id = UUID.randomUUID().toString().substring(0,4);
        this.name = name;
        this.email = email;
        this.department = department;
        this.ativo = true;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
