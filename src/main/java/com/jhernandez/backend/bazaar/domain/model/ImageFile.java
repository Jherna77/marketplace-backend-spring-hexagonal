package com.jhernandez.backend.bazaar.domain.model;

/*
 * Model class representing an image file.
 */
public class ImageFile {

    private byte[] data;
    private String fileName;
    private String contentType;
    private String imageUrl;

    public ImageFile(byte[] data, String fileName, String contentType, String imageUrl) {
        this.data = data;
        this.fileName = fileName;
        this.contentType = contentType;
        this.imageUrl = imageUrl;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
