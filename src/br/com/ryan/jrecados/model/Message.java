package br.com.ryan.jrecados.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Message {
    private final String id;
    private User sender; //Remetente
    private String content; //conteúdo
    private  User destination;
    private final LocalDateTime timestamp;

    public Message(User sender, String content, User destination) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.content = content;
        this.destination = destination;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
