package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.img.location}")
    String imgPath;

    @Override
    public String storeInFileSystem(MultipartFile file) {
        if(file == null)
            throw new IllegalArgumentException("Imagen incorrecta");

        String fileNameWithExtension = file.getOriginalFilename();
        if (!StringUtils.hasLength(fileNameWithExtension))
            throw new IllegalArgumentException("Imagen incorrecta");

        String extension = StringUtils.getFilenameExtension(fileNameWithExtension);
        if (!StringUtils.hasLength(extension))
            throw new IllegalArgumentException("Imagen incorrecta");

        String fileName = fileNameWithExtension.replace("." + extension, "");
        String fileNameToSave = fileName + "_" + System.currentTimeMillis() + "." + extension;

        try {
            Path path = Paths.get(imgPath).resolve(fileNameToSave);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al guardar la imagen");
        }
        return fileNameToSave;
    }

    @Override
    public Resource loadFile(String fileName) {
        Path path = Paths.get(imgPath).resolve(fileName);
        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if(!resource.exists() || !resource.isReadable())
            throw new RuntimeException();

        return resource;
    }
}
