package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.RetrievePreferencesUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdatePreferencesUseCase;

/*
 * Port interface for preferences service operations.
 */
public interface PreferencesServicePort extends RetrievePreferencesUseCase, UpdatePreferencesUseCase {

}
