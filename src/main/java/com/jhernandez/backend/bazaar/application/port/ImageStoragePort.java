package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

/*
 * Port interface for image storage operations.
 */
public interface ImageStoragePort {

        ImageFile saveImage(ImageFile image) throws ImageFileException;

        ImageFile getImageByFileName(String fileName) throws ImageFileException;

        void deleteImageByFilename(String fileName) throws ImageFileException;

}
