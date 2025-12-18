package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.model.Message;

/*
 * Use case interface for creating a message.
 */
public interface CreateMessageUseCase {

    public void createMessage(Message message) throws MessageException;

}
