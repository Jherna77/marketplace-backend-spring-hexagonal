package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for backup information.
 */
@Getter
@Setter
public class BackupDto {

    Long id;
    String createdAt;
    String dataBaseFileName;
    String imagesFileName;

}
