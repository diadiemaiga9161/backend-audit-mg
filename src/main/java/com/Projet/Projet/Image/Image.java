package com.Projet.Projet.Image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Image {

    public static String save(MultipartFile file, String fileName, String folderName) {
        String src = "";

        try {
            String uploadDir = "src/main/resources/static/"; // Remplacez par le chemin de votre répertoire de ressources statiques

            Path uploadPath = Paths.get(uploadDir + folderName);
            Path filePath = uploadPath.resolve(fileName);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            src = "/"+folderName + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            src = null;
        }

        return src;
    }
}
