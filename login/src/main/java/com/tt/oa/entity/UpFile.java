package com.tt.oa.entity;

public class UpFile {
    String uploadFile;

    @Override
    public String toString() {
        return "UpFile{" +
                "uploadFile='" + uploadFile + '\'' +
                '}';
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }
}
