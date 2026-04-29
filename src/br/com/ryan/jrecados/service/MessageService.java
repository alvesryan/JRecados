package br.com.ryan.jrecados.service;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.repository.MessageRepository;

import java.util.List;

public class MessageService {
    private MessageRepository repository = new MessageRepository();

    public void createMessage(Message newMessage){
        if (newMessage.getContent() == null || newMessage.getContent().trim().isEmpty()){
            throw new IllegalArgumentException("Não se pode enviar um recado vázio");

        } else if (newMessage.getSender() == null || newMessage.getDestination() == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        repository.create(newMessage);
    }

    //Método para que o usuário, só consiga ver, as mensagens que ele enviou ou recebeu.
    public List<Message> userMessage(String idUser){
        List<Message> allMessages = repository.findAll(); //Criando uma lista que vai receber todas as mensagens que ja foram enviadas

        return allMessages.stream()
                .filter(msg -> { //filtrando a lista, para que só apareça as mensagens que o usuário recebeu ou enviou
                    boolean sender = msg.getSender().getId().equals(idUser);
                    boolean destination = msg.getDestination().getId().equals(idUser);

                    return sender || destination;
                })
                .toList();
    }

    public void deleteMessage (String idMessage){
        if(idMessage == null || idMessage.trim().isEmpty()){
            throw new IllegalArgumentException("Mensagem vazia.");
        }
        //falta aplicar a lógica de filtro, para que o usuário só consiga apagar a suas mensagens
        repository.delete(idMessage);

    }
}
