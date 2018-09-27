package com.tsn.util;

public class MimeTypeHelper {

    public MimeTypeHelper(){
    }

    public String getMimiTypeByFileType(String fileType) {
        if(fileType.equals("pdf")) {
            return "application/pdf";
        } else if(fileType.equals("jpeg")) {
            return "image/jpeg";
        } else if(fileType.equals("html")) {
            return "text/html";
        }  else if(fileType.equals("txt")) {
            return "text/plain";
        }  else if(fileType.equals("xls")) {
            return "application/vnd.ms-excel";
        }  else if(fileType.equals("xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if(fileType.equals("doc")) {
            return "application/msword";
        } else if(fileType.equals("docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }

        return "application/octet-stream";
    }
}
