package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.repository.MessageRepository;
import br.com.ryan.jrecados.repository.UserRepository;
import br.com.ryan.jrecados.service.MessageService;

import java.util.Scanner;

public class uiMain {

    public void menuMain(){

        UserRepository userRepository = new UserRepository();
        MessageService messageService = new MessageService();

        uiCreateMessage createMessageUi = new uiCreateMessage(userRepository, messageService);
        uiCreateUser createUserUi = new uiCreateUser(userRepository);

        Scanner scanner = new Scanner(System.in);
        int cont = -1;

        do {
            System.out.println("\n:: JRecados ::");
            System.out.println(":: 1. Registar Conta     ::");
            System.out.println(":: 2. Enviar recado      ::");
            System.out.println(":: 3. Ler recados        ::");
            System.out.println(":: 4. Ler meus recados   ::");
            System.out.println(":: 5. Apagar recado      ::");
            System.out.println(":: 0. Fechar             ::");
            System.out.print("\nEscolhe uma opção: ");
            cont = scanner.nextByte();

            switch (cont){
                case 1:
                    createUserUi.createUserScreen();
                    break;
                case 2:
                    createMessageUi.createMessageScreen();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }

        } while (cont != 0);
    }
}