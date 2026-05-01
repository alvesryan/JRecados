package br.com.ryan.jrecados.repository;

import br.com.ryan.jrecados.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRepository {
    Map<String, Message> messageDb = new HashMap<>();

    //CREATE/UPDATE
    public void create(Message newMessage){
        messageDb.put(newMessage.getId(), newMessage); //Adicionando a mensagem dentro do Map
    }

    //READ
    public List<Message> findAll(){
        return new ArrayList<>(messageDb.values()); // passando todos os dados do Map, para a List
    }

    //DELETE
    public void delete(String idMessage) {
        if (messageDb.containsKey(idMessage)) {
            messageDb.remove(idMessage);
            System.out.println("Mensagem apagada.");
        } else {
            System.out.println("Nenhuma mensagem encontrada");
        }
    }
}