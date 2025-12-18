package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.MessageRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.MessageServicePort;
import com.jhernandez.backend.bazaar.domain.exception.MessageException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Message;

/*
 * Service class for message operations.
 */
public class MessageService implements MessageServicePort {

    private final MessageRepositoryPort messageRepository;

    public MessageService(MessageRepositoryPort messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void createMessage(Message message) throws MessageException {
        messageRepository.saveMessage(message);
    }

    @Override
    public List<Message> findAllMessages() {
        return messageRepository.findAllMessages();
    }

    @Override
    public List<Message> findMessagesByRecipientId(Long recipientId) throws UserException {
        if (recipientId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return messageRepository.findMessagesByRecipientId(recipientId);
    }

    @Override
    public Message findMessageById(Long id) throws MessageException {
        if (id == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        return messageRepository.findMessageById(id)
                .orElseThrow(() -> new MessageException(ErrorCode.MESSAGE_NOT_FOUND));
    }

    @Override
    public Boolean hasNewMessages(Long recipientId) throws UserException {
        if (recipientId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return messageRepository.hasNewMessages(recipientId);
    }

    @Override
    public void setMessageAsSeen(Long messageId) throws MessageException {
        if (messageId == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        Message message = findMessageById(messageId);
        message.setSeen(true);
        messageRepository.saveMessage(message);
    }

    @Override
    public List<Message> deleteMessageById(Long id) throws MessageException {
        if (id == null) {
            throw new MessageException(ErrorCode.MESSAGE_ID_NOT_NULL);
        }
        Message message = findMessageById(id);
        messageRepository.deleteMessageById(id);
        return messageRepository.findMessagesByRecipientId(message.getRecipient().getId());
    }

}
