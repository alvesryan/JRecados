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

    //Usado pelo MessageService para filtrar dados
    public List <Message> findAll(){
        return new ArrayList<>(messageDb.values()); //Criando uma lista que contém todos os dados da Message
    }

    //Usado pelo MessageService para verificar o dono da mensagem
    public Message findById(String idMessage){
        return messageDb.get(idMessage); //retorna apenas a mensagem que contém o Id passado
    }

    //DELETE
    public void delete(String idMessage) {
        messageDb.remove(idMessage);
    }

}