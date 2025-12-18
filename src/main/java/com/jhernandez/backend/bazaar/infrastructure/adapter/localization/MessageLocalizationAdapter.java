package com.jhernandez.backend.bazaar.infrastructure.adapter.localization;

import com.jhernandez.backend.bazaar.application.port.MessageLocalizationPort;

import lombok.AllArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;


/*
 * Adapter class for localizing messages using Spring's MessageSource.
 * Implements the MessageLocalizationPort interface.
 */
@Service
@AllArgsConstructor
public class MessageLocalizationAdapter implements MessageLocalizationPort {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Map<String, Object> args) {
        String pattern = messageSource.getMessage(code, null, Locale.getDefault());
        String result = pattern;

        for (Map.Entry<String, Object> entry : args.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
        }

        return result;
    }
}
