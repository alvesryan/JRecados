package br.com.ryan.jrecados;

import br.com.ryan.jrecados.model.Message;
import br.com.ryan.jrecados.model.User;
import br.com.ryan.jrecados.repository.MessageRepository;
import br.com.ryan.jrecados.repository.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepo = new UserRepository();
        MessageRepository messageRepo = new MessageRepository();


        User user1 = new User("Ryan", "ryanalves1100@gmail.com", "TI");
        User user2 = new User("Joao", "joaofelipe@gmail.com", "RH");


        userRepo.save(user1);
        userRepo.save(user2);

        // Escrevendo o recado
        Message message = new Message(user1, "HelLo World", user2);


        messageRepo.save(message); //Salvando a mensagem


        List<Message> recadosDoJoao = messageRepo.findByDestination("Joao");

        System.out.println("--- MESSAGE SYSTEM ---");
        System.out.println("Messages for Joao: " + recadosDoJoao.size());

        // Só tenta imprimir os detalhes se a lista não estiver vazia
        if (!recadosDoJoao.isEmpty()) {
            for (Message m : recadosDoJoao) {
                System.out.println("By: " + m.getSender().getName());
                System.out.println("For: " + m.getDestination().getName());
                System.out.println("Message: " + m.getContent());
                System.out.println("Hour: " + m.getTimestamp());
                System.out.println("-------------------------");
            }
        }
    }
}