package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing a message record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserEntity recipient;
    
    @Column(name = "message_date", nullable = false)
    private LocalDateTime messageDate;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean seen;

}
