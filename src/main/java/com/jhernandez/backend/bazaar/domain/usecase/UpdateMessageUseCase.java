package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.MessageException;

/*
 * Use case interface for updating messages.
 */
public interface UpdateMessageUseCase {

    void setMessageAsSeen(Long messageId) throws MessageException;

}
