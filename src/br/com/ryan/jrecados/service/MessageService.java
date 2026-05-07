package br.com.ryan.jrecados.service;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.repository.MessageRepository;

import java.util.List;

public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void createMessage(Message newMessage){
        if (newMessage.getContent() == null || newMessage.getContent().trim().isEmpty()){
            throw new IllegalArgumentException("Não se pode enviar um recado vázio");

        } else if (newMessage.getSender() == null || newMessage.getDestination() == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        messageRepository.create(newMessage);
    }

    public void updateMessage(String idMessage, String idUser, String content){
        if(idMessage == null || idMessage.trim().isEmpty() || idUser == null || idUser.trim().isEmpty()){
            throw new IllegalArgumentException("O Id não pode ser vazio");
        }

        if(content == null || content.trim().isEmpty()){
            throw new IllegalArgumentException("Novo recado vazio!");
        }

        Message message = messageRepository.findById(idMessage);

        if(message == null){
            throw new IllegalArgumentException("Nenhuma mensagem encontrada!");
        }

        if(!message.getSender().getId().equals(idUser)){
            throw new SecurityException("Não autorizado!");
        }

        message.setContent(content);
    }

    //Método para que o usuário, só consiga ver, as mensagens que ele enviou ou recebeu.
    public List<Message> readMessage(String idUser){
        List<Message> allMessages = messageRepository.findAll(); //Criando uma lista que vai receber todas as mensagens que ja foram enviadas

        return allMessages.stream()
                .filter(msg -> { //filtrando a lista, para que só apareça as mensagens que o usuário recebeu ou enviou
                    boolean sender = msg.getSender().getId().equals(idUser);
                    boolean destination = msg.getDestination().getId().equals(idUser);

                    return sender || destination;
                })
                .toList();
    }

    public void deleteMessage (String idMessage, String idUserLogado){
        Message message = messageRepository.findById(idMessage); //Criando o objeto que vai receber a mensagem, cuja o Id seja igual ao dos parâmetros.

        if(idMessage == null || idMessage.trim().isEmpty()){
            throw new IllegalArgumentException("Mensagem vazia.");
        }

        if(message == null){
            throw new IllegalArgumentException("Nenhuma mensagem encontrada");
        }

        if(!message.getSender().getId().equals(idUserLogado)){
            throw new SecurityException("Apenas quem enviou esse recado, pode apaga-lo");
        }
        messageRepository.delete(idMessage);
    }
}
