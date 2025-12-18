package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.model.Message;

/*
 * Use case interface for deleting a message.
 */
public interface DeleteMessageUseCase {

    List<Message> deleteMessageById(Long id) throws MessageException;

}
