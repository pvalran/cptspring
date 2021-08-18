package com.Xoot.CreditoParaTi.utils;

import com.Xoot.CreditoParaTi.entity.Document;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class DocumentUtil {
    public DocumentUtil(){

    }

    public String DocBase64(Document document) {
        Path rutaArchivo = Paths.get("/srv/www/upload").resolve(document.getName()).toAbsolutePath();

        File file = rutaArchivo.toFile();

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String base64File = Base64.getEncoder().encodeToString(fileContent);
            return base64File;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getCause().getMessage();
        }
    }
}
