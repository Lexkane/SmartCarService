package ua.ita.smartcarservice.service.files;

import org.springframework.web.multipart.MultipartFile;

public interface CarNormsService {

    void storeCarNorms(MultipartFile file);
}
