package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

/*
 * Model class representing a backup entity.
 */
public class Backup {

    private Long id;
    private LocalDateTime createdAt;
    private String dataBaseFileName;
    private String imagesFileName;

    public Backup() {}

    public Backup(Long id, LocalDateTime createdAt, String dataBaseFileName, String imagesFileName) {
        this.id = id;
        this.createdAt = createdAt;
        this.dataBaseFileName = dataBaseFileName;
        this.imagesFileName = imagesFileName;
    }

    public Backup(String dataBaseFileName, String imagesFileName) {
        this.id = null;
        this.createdAt = LocalDateTime.now();
        this.dataBaseFileName = dataBaseFileName;
        this.imagesFileName = imagesFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDataBaseFileName() {
        return dataBaseFileName;
    }

    public void setDataBaseFileName(String dataBaseFileName) {
        this.dataBaseFileName = dataBaseFileName;
    }

    public String getImagesFileName() {
        return imagesFileName;
    }

    public void setImagesFileName(String imagesFileName) {
        this.imagesFileName = imagesFileName;
    }

}
