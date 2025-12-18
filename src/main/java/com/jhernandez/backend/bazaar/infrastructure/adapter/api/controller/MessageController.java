package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.MessageServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.MessageDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.MESSAGES;

/*
 * Controller for message-related endpoints.
 */
@RestController
@RequestMapping(MESSAGES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageServicePort messageService;
    private final MessageDtoMapper messageDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> findMessageById(@PathVariable Long id) {
        log.info("Finding message with ID {}", id);
        return ResponseEntity.ok(messageDtoMapper.toDto(messageService.findMessageById(id)));
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> findMessagesByUserId(@PathVariable Long recipientId) {
        log.info("Finding messages for user with ID {}", recipientId);
        return ResponseEntity.ok(messageService.findMessagesByRecipientId(recipientId).stream()
            .map(messageDtoMapper::toDto)
            .toList());
    }

    @GetMapping("/recipient/{recipientId}/new")
    public ResponseEntity<?> hasNewMessages(@PathVariable Long recipientId) {
        log.info("Checking for new messages for user with ID {}", recipientId);
        return ResponseEntity.ok(messageService.hasNewMessages(recipientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> setMessageAsSeen(@PathVariable Long id) {
        log.info("Setting message with ID {} as seen", id);
        messageService.setMessageAsSeen(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        log.info("Deleting message with ID {}", id);
        return ResponseEntity.ok(messageService.deleteMessageById(id).stream()
            .map(messageDtoMapper::toDto)
            .toList());
    }

}
