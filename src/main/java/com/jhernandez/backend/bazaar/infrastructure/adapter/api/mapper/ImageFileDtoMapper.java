package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ImageFileDto;

/*
 * Mapper class for converting ImageFile domain models to ImageFileDto objects and vice versa.
 * Handles conversion from MultipartFile to ImageFile, including error handling.
 */
@Component
public class ImageFileDtoMapper {

    public ImageFileDto toDto(ImageFile imageFile) {
        return new ImageFileDto(
                imageFile.getImageUrl());
    }

    public ImageFile toDomain(MultipartFile multipartFile) throws ImageFileException {
        try {
            return multipartFile != null
                    ? new ImageFile(
                            multipartFile.getBytes(),
                            multipartFile.getOriginalFilename(),
                            multipartFile.getContentType(),
                            null)
                    : null;
        } catch (IOException e) {
            throw new ImageFileException(ErrorCode.IMAGE_ERROR);
        }
    }

    public List<ImageFile> toDomainList(List<MultipartFile> multipartFileList) throws ImageFileException {
        if (multipartFileList == null) return null;

        List<ImageFile> result = new ArrayList<>();
        for (MultipartFile file : multipartFileList) {
            result.add(toDomain(file)); 
        }
        return result;
    }

    public List<ImageFileDto> toDtoList(List<ImageFile> imageFileList) {
        return imageFileList.stream()
                .map(this::toDto)
                .toList();
    }
}
