package br.com.ryan.jrecados.repository;

import br.com.ryan.jrecados.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void save(User user){
        this.users.add(user);
    }

    public List<User> findAll(){ //Metodo que exibe toda a lista
        return this.users;
    }

    public User findById(String idBusca){
        for(User u : this.users){
            if(u.getId().equals(idBusca)) {
                return u;
            }
        }
        return null;
    }
}
