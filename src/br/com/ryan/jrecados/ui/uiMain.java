package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.repository.MessageRepository;
import br.com.ryan.jrecados.repository.UserRepository;
import br.com.ryan.jrecados.service.MessageService;
import br.com.ryan.jrecados.service.UserService;

import java.util.Scanner;
import java.util.InputMismatchException;

public class uiMain {

    public void menuMain(){

        // 1. Criamos os Bancos de Dados (Os Estoques)
        UserRepository userRepository = new UserRepository();
        MessageRepository messageRepository = new MessageRepository();

        // 2. Criamos os Serviços e injetamos os bancos neles (Os Chefs de Cozinha)
        UserService userService = new UserService(userRepository);
        MessageService messageService = new MessageService(messageRepository);

        // 3. Criamos as Telas e injetamos os Serviços nelas (Os Garçons)
        // Nota: Garanta que o construtor do uiUser esteja pedindo um UserService agora!
        uiMessage uiMessage = new uiMessage(userRepository, messageService);
        uiUser uiUser = new uiUser(userRepository, userService);

        Scanner scanner = new Scanner(System.in);
        int cont = -1;

        do {
            try {
                System.out.println("\n:: JRecados ::");
                System.out.println(":: 1. Registar User      ::");
                System.out.println(":: 2. Visualizar Users   ::");
                System.out.println(":: 3. Atualizar User     ::");
                System.out.println(":: 4. Delete User        ::");
                System.out.println(":: 5. Enviar recado      ::");
                System.out.println(":: 6. Ler recados        ::");
                System.out.println(":: 7. Atualizar recado   ::");
                System.out.println(":: 8. Apagar recado      ::");
                System.out.println(":: 0. Fechar             ::");
                System.out.print("\nEscolhe uma opção: ");

                cont = scanner.nextByte();
                scanner.nextLine(); // limpando o teclado

                switch (cont){
                    case 1:
                        uiUser.createUserScreen();
                        break;
                    case 2:
                        uiUser.readUserScreen();
                        break;
                    case 3:
                        uiUser.updateUserScreen();
                        break;
                    case 4:
                        uiUser.deleteUserScreen();
                        break;
                    case 5:
                        uiMessage.createMessageScreen();
                        break;
                    case 6:
                        uiMessage.readMessageScreen();
                        break;
                    case 7:
                        uiMessage.updateMessageScreen();
                        break;
                    case 8:
                        uiMessage.deleteMessageScreen();
                        break;
                    case 0:
                        System.out.println("saindo...");
                        break;
                    default:
                        System.out.println("\nOpção inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nDigite apenas números no menu!");
                scanner.nextLine();
                cont = -1; // mantém o looping
            }

        } while (cont != 0);
    }
}