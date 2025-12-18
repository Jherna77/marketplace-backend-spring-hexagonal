package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Message;

/*
 * Use case interface for retrieving messages.
 */
public interface RetrieveMessageUseCase {

    List<Message> findAllMessages();

    List<Message> findMessagesByRecipientId(Long receiverId) throws UserException;

    Message findMessageById(Long id) throws MessageException;

    Boolean hasNewMessages(Long recipientId) throws UserException;

}
