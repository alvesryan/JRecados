package br.com.ryan.jrecados.service;

import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User newUser){
        if(newUser == null){
            throw new IllegalArgumentException("User vazio");
        }

        if(newUser.getName().trim().isEmpty() || newUser.getEmail().trim().isEmpty() || newUser.getDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Argumento vazio!");
        }

        userRepository.create(newUser);
    }

    public void updateUser(String idUser, String nameUser, String emailUser, String departmentUser){
        if( idUser.trim().isEmpty()||nameUser.trim().isEmpty() || emailUser.trim().isEmpty() || departmentUser.trim().isEmpty()){
            throw new IllegalArgumentException("Argumento ilegal");
        }

        User user = userRepository.findById(idUser);

        if(user == null){
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if(!user.getId().equals(idUser)){
            throw new SecurityException("Não autorizado!");
        }

        user.setName(nameUser);
        user.setEmail(emailUser);
        user.setDepartment(departmentUser);
    }

    public List<User> readUsers(){
        List<User> allUsers = userRepository.findAll();

        if(allUsers == null || allUsers.isEmpty()){
            throw new IllegalArgumentException("Nenhum usuário encontrado!");
        }

        List<User> usersAtivos = allUsers.stream() //filtrando apenas os usuários ativos
                .filter(User::isAtivo)
                .toList();

        if(usersAtivos.isEmpty()){
            throw new IllegalArgumentException("Não há utilizadores ativos no momento!");
        }

        return usersAtivos;
    }

    public void deleteUser(String idUser){

        if(idUser == null || idUser.trim().isEmpty()){
            throw new IllegalArgumentException("Id vazio");
        }

        User user = userRepository.findById(idUser);

        if(user == null){
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        user.setAtivo(false);
    }
}
