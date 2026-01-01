package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

/*
 * Message domain model representing a message entity.
 */
public class Message {

    private Long id;
    private User recipient;
    private LocalDateTime messageDate;
    private String content;
    private Boolean seen;

    public Message() {
    }

    public Message(Long id, User recipient, LocalDateTime messageDate, String content, Boolean seen) {
        this.id = id;
        this.recipient = recipient;
        this.messageDate = messageDate;
        this.content = content;
        this.seen = seen;
    }

    public Message(User recipient, String content) {
        this.id = null;
        this.recipient = recipient;
        this.content = content;
        this.messageDate = LocalDateTime.now();
        this.seen = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

}
