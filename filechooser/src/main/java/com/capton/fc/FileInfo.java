package com.capton.fc;

/**
 * Created by capton on 2018/1/6.
 */

public class FileInfo {
    public static final String FILE_TYPE_FOLDER="type_folder";
    public static final String FILE_TYPE_VIDEO="type_video";
    public static final String FILE_TYPE_AUDIO="type_audio";
    public static final String FILE_TYPE_FILE="type_file";
    public static final String FILE_TYPE_APK="type_apk";
    public static final String FILE_TYPE_ZIP="type_zip";
    public static final String FILE_TYPE_RAR="type_rar";
    public static final String FILE_TYPE_JPEG="type_jpeg";
    public static final String FILE_TYPE_JPG="type_jpg";
    public static final String FILE_TYPE_PNG="type_png";

    private String fileType = FILE_TYPE_FOLDER;

    private boolean isFolder;
    private String fileName;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
