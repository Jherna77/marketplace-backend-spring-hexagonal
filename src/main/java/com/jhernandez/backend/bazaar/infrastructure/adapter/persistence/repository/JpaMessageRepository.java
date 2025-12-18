package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.MessageEntity;

/*
 * Repository interface for MessageEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByRecipientId(Long recipientId);

    Boolean existsByRecipientIdAndSeenFalse(Long recipientId);

}
