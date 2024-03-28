package com.co.orientationVocational.app.business.files;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class prueba {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath = file.getOriginalFilename();
        System.out.println(filePath);
        // Aquí puedes trabajar con la ruta real del archivo
        return "Archivo subido exitosamente. Ruta: " + filePath;
    }
}