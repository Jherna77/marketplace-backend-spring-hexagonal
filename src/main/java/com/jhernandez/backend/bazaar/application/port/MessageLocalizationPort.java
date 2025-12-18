package com.jhernandez.backend.bazaar.application.port;

import java.util.Map;

/*
 * Port interface for message localization operations.
 */
public interface MessageLocalizationPort {

    String getMessage(String key, Map<String, Object> args);

}
