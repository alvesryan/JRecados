package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.UserRepository;
import br.com.ryan.jrecados.service.UserService;

import java.util.Scanner;

public class uiUser {

    private final UserRepository userRepository;//repositório para guardar o usuário criado
    UserService userService;

    public uiUser(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void createUserScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n:: Registar Novo Utilizador ::");

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Departamento: ");
        String department = scanner.nextLine();

        User newUser = new User(name, email, department); //criando user

        userService.createUser(newUser); //adicionando na memória;

        System.out.println("\n Usuário criado com sucesso!");
        System.out.println("Seu ID: " + newUser.getId() + " ⚠️");
        System.out.println("Vais precisar deste ID para enviar e receber recados.");
    }

    public void updateUserScreen(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(":: Atualizar usuário existente ::");
        System.out.println("Digite seu Id");
        String idUser = scanner.nextLine().trim();

        try{
            User user = userRepository.findById(idUser);

            if(user == null){
                System.out.println("Usuário não encontrado");
                return;
            }

            System.out.println(":: Dados atuais ::");
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Departamento " + user.getDepartment());
            System.out.println("------------------------------");

            System.out.println(":: Caso não queria atualizar algum dado, reescreva o dado antigo ::");
            System.out.println("Name: ");
            String newName = scanner.nextLine();

            System.out.println("Email: ");
            String newEmail = scanner.nextLine();

            System.out.println("Departamento: ");
            String newDepartment = scanner.nextLine();

            userService.updateUser(idUser, newName, newEmail, newDepartment);

            System.out.println("Usuário atualizado");

        } catch (IllegalArgumentException | SecurityException e){
            System.out.println("Não foi possível atualizar " + e);
        }
    }

    public void readUserScreen(){

        System.out.println("\n:: Lista de Usuários ::");
        try {
            var listUsers = userService.readUsers();
            for(User u: listUsers){
                System.out.println("Id: " + u.getId());
                System.out.println("Name: " + u.getName());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Department: " + u.getDepartment());
                System.out.println("-----------------------------------");
            }
        } catch (IllegalArgumentException e) {
            System.out.println( e.getMessage());
        }
    }

    public void deleteUserScreen(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(":: Excluir User ::");
        System.out.println("Digite seu ID");
        String idUser = scanner.nextLine();

        var user = userRepository.findById(idUser);

        if(user == null){
            System.out.println("Usuário não encontrado");
            return;
        }

        userService.deleteUser(idUser);

        System.out.println("Usuário deletado.");
    }

}