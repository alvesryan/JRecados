package br.com.ryan.jrecados.ui;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.UserRepository;
import br.com.ryan.jrecados.service.MessageService;

import java.util.Scanner;

public class uiCreateMessage {

    private final UserRepository userRepository;
    private final MessageService messageService;

    public uiCreateMessage(UserRepository userRepository, MessageService messageService) {
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
            messageService.createMessage(newMessage); // Manda pro  Service validar e salvar
            System.out.println("\nMensagem enviada com sucesso para " + destinatario.getName() + "!");
        } catch (IllegalArgumentException e) {
            // Se você tentar mandar uma mensagem vazia, o seu Service vai estourar um erro,
            // e o catch vai pegar ele aqui para não quebrar o programa!
            System.out.println("\nErro ao enviar: " + e.getMessage());
        }
    }
}