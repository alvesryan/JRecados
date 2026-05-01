package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.UserRepository;

import java.util.Scanner;

public class uiCreateUser {

    private final UserRepository userRepository; //repositório para guardar o usuário criado

    public uiCreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        userRepository.create(newUser); //adicionando na memória;

        System.out.println("\n Usuário criado com sucesso!");
        System.out.println("Seu ID: " + newUser.getId() + " ⚠️");
        System.out.println("Vais precisar deste ID para enviar e receber recados.");
    }
}