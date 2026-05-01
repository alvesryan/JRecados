package br.com.ryan.jrecados.repository;

import br.com.ryan.jrecados.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userDb = new HashMap<>();

    //adicionando/atualizando usuário dentro do map
    public void create(User newUser){
        userDb.put(newUser.getId(), newUser);
    }

    //Listando os usuários
    public List<User> findALl(){
        return new ArrayList<>(userDb.values());
    }

    public User findById(String idUser){
        return userDb.get(idUser);
    }

    public void delete(String idUser){
        if(userDb.containsKey(idUser)){
            userDb.remove(idUser);
            System.out.println("Usuário deletado");
        } else {
            System.out.println("Usuário não encontrado");
        }
    }


}