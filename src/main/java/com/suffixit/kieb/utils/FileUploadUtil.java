package com.suffixit.kieb.utils;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileNameToSave,
                                MultipartFile fileName) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = fileName.getInputStream()) {
            Path filePath = uploadPath.resolve(fileNameToSave);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName.getOriginalFilename(), ioe);
        }
    }


    public static boolean isImageFile(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        String mimeType = tika.detect(file.getInputStream());
        return mimeType != null && mimeType.startsWith("image");
    }


}


