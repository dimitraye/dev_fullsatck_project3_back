package com.chatop.rentalApp_back.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) {
        try {
            // Crée un répertoire de stockage s'il n'existe pas.
            Path storageDir = Paths.get(uploadDir);
            if (!Files.exists(storageDir)) {
                Files.createDirectories(storageDir);
            }

            // Crée un nom de fichier unique pour éviter les conflits.
            String fileName = "image_" + UUID.randomUUID() + ".jpg";
            Path filePath = storageDir.resolve(fileName);

            // Stocke le fichier dans le répertoire de stockage.
            Files.write(filePath, file.getBytes());

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur, par exemple, en lançant une exception personnalisée.
            return null;
        }
    }
}