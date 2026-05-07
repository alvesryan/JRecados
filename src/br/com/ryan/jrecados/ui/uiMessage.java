package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.UserRepository;
import br.com.ryan.jrecados.service.MessageService;

import java.util.Scanner;

public class uiMessage {

    private final UserRepository userRepository;
    private final MessageService messageService;

    public uiMessage(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    public void createMessageScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n:: Enviar Novo Recado ::");
        System.out.print("Digite o SEU ID (Remetente): ");
        String idRemetente = scanner.nextLine();
        User remetente = userRepository.findById(idRemetente);

        if (remetente == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        System.out.print("Digite o ID do Destinatário: ");
        String idDestinatario = scanner.nextLine();
        User destinatario = userRepository.findById(idDestinatario);

        if (destinatario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        System.out.print("Recado: ");
        String conteudo = scanner.nextLine();

        try {
            Message newMessage = new Message(remetente, conteudo, destinatario);
            messageService.createMessage(newMessage); // Manda para o Service validar e salvar
            System.out.println("\nMensagem enviada com sucesso para " + destinatario.getName() + "!");
        } catch (IllegalArgumentException e) {
            // Se você tentar mandar uma mensagem vazia, o seu Service vai estourar um erro,
            // e o catch vai pegar ele aqui para não quebrar o programa!
            System.out.println("\nErro ao enviar: " + e.getMessage());
        }
    }

    public void readMessageScreen(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(":: Messages ::");
        System.out.println(":: Digite seu Id ::");
        String idUser = scanner.nextLine();
        User userLogado = userRepository.findById(idUser); //

        if(userLogado == null){
            System.out.println("O Id não existe");
            return;
        }

        var messageUser = messageService.readMessage(idUser);//buscando a lista de mensagens do user

        if(messageUser.isEmpty()){
            System.out.println("Caixa de recados vazia!");
            return;
        }

        System.out.println("\n:: suas mensagens ::");
        for(Message m : messageUser){
            System.out.println("Id: "+ m.getId());
            System.out.println("By: " + m.getSender().getName());
            System.out.println("For: " + m.getDestination().getName());
            System.out.println("Content: " + m.getContent());
        }
        System.out.println("-------------------------");

    }

    public void updateMessageScreen(){
        Scanner scanner = new Scanner(System.in);
        int cont;

        do {
            System.out.println("\n:: Atualizar recado ::");
            System.out.print("Digite o SEU ID: ");
            String idUser = scanner.nextLine();
            User user = userRepository.findById(idUser);

            var messageUser = messageService.readMessage(idUser);//buscando a lista de mensagens do user

            if (user == null) {
                System.out.println("Usuário não encontrado");
            }

            if(messageUser.isEmpty()){
                System.out.println("Caixa de recados vazia!");
                return;
            }

            System.out.println("\n:: Escolha qual recado deseja atualizar ::");
            for(Message m : messageUser){
                System.out.println("Id: "+ m.getId());
                System.out.println("By: " + m.getSender().getName());
                System.out.println("For: " + m.getDestination().getName());
                System.out.println("Content: " + m.getContent());
            }
            System.out.println("-------------------------");

            System.out.println("Copie e cole o ID da mensagem que deseja editar: ");
            String idMessage = scanner.next().trim();

            System.out.println("Digite o novo recado:");
            String newContent = scanner.next().trim();

            try{
                messageService.updateMessage(idMessage, idUser, newContent);
                System.out.println("Recado atualizado!");
            } catch (IllegalArgumentException | SecurityException e){
                System.out.println("Erro ao tentar atualizar!" + e);
            }

            System.out.println("Digite");
            System.out.println("Enter. p/continuar");
            System.out.println("0. p/sair");
            cont = scanner.nextInt();

        } while (cont != 0);
    }

    public void deleteMessageScreen(){
        Scanner scanner = new Scanner(System.in);
        int cont;
        do {
            System.out.println(":: Delete recados ::");
            System.out.println(":: Digite o seu Id.");
            String idUser = scanner.nextLine();

            var messageUser = messageService.readMessage(idUser);

            if(messageUser.isEmpty()){
                System.out.println("Caixa de recados vazia.");
                return;
            }

            System.out.println("\n:: Escolha um recado que deseja apagar ::");
            for(Message msg : messageUser){
                System.out.println("Id: "+ msg.getId());
                System.out.println("By: " + msg.getSender().getName());
                System.out.println("For: " + msg.getDestination().getName());
                System.out.println("Content: " + msg.getContent());
            }
            System.out.println("-------------------------");

            System.out.println("Copie e cole o Id da mensagem aqui:");
            String idMessage = scanner.nextLine().trim();

            try{
                messageService.deleteMessage(idMessage, idUser);
                System.out.println("Recado apagado");
            } catch (IllegalArgumentException | SecurityException e){
                System.out.println("Não foi possível realizar a operação " + e.getMessage());
            }

            System.out.println("Digite");
            System.out.println("Enter. p/continuar");
            System.out.println("0. p/sair");
            cont = scanner.nextInt();

        } while (cont != 0);
    }

}