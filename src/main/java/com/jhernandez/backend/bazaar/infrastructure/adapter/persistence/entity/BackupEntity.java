package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/*
 * Entity class representing a backup record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "backups")
public class BackupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)    
    private LocalDateTime createdAt;

    @Column(name ="database_file_name")
    private String dataBaseFileName;

    @Column(name = "images_file_name")
    private String imagesFileName;
    
}
