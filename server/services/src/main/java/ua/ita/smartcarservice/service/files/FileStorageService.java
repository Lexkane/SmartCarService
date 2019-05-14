package ua.ita.smartcarservice.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {

    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public Path getFileStorageLocation();

    public String storeFile(MultipartFile file, Long id, String username);

}
