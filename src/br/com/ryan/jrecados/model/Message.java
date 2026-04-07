package br.com.ryan.jrecados.model;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private User sender; //Remetente
    private String content; //conteudo
    private  User destination;
    private LocalDateTime timestamp;

    public Message(User sender, String content, User destination) {
        this.sender = sender;
        this.content = content;
        this.destination = destination;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
