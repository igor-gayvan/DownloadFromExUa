/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadfromexua;

/**
 *
 * @author Igor Gayvan
 */
public class DownloadFile {

    private String fileName;
    private Long fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public DownloadFile() {
    }

    public DownloadFile(String fileName, Long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

}
