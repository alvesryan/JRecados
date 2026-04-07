package br.com.ryan.jrecados.repository;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.model.User;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private List<Message> messagens;

    public MessageRepository(){
        this.messagens = new ArrayList<>();
    }

    public void save(Message message){
        this.messagens.add(message);
    }

    public List<Message> findAll(){
        return this.messagens;
    }

    public List<Message> findByDestination(String destinationName) {

        List<Message> foundMessages = new ArrayList<>(); //Armazena as mensagens encontradas

        for(Message m : this.messagens) {
            if(m.getDestination().getName().equals(destinationName)) { //Se o parametro for igual ao nome destinatario
                foundMessages.add(m); // Adicionamos na lista
            }
        }
        return foundMessages;
    }


    public List<Message> findBySenderName(String senderName) {

        List<Message> foundMessages = new ArrayList<>();

        for(Message m : this.messagens) {
            // Repare na diferença aqui: pegamos o Objeto Sender, e depois a String Name dele!
            if(m.getSender().getName().equals(senderName)) {
                foundMessages.add(m);
            }
        }

        return foundMessages;
    }
}
