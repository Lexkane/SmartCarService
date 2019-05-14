package ua.ita.smartcarservice.controller.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.ita.smartcarservice.dto.files.AvatarDto;
import ua.ita.smartcarservice.dto.files.UploadFileResponse;
import ua.ita.smartcarservice.service.files.AvatarService;
import ua.ita.smartcarservice.service.files.HashService;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.files.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private HashService hashService;

    @Autowired
    private UserService userService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Long id) {
        String username = userService.getUserById(id).getUsername();
        String fileName = fileStorageService.storeFile(file, id, username);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        String originalFileName = file.getOriginalFilename();
        String fileNameHash = hashService.makeHash(id, username, originalFileName);
        avatarService.addAvatarToUser(new AvatarDto(id,
                fileDownloadUri,
                (fileStorageService.getFileStorageLocation().toAbsolutePath().toString() + "\\" + fileNameHash + file.getContentType())));
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException ex) {
            logger.error("Could not determine file type.");
        }
        String contentType = request.getServletContext().getMimeType(file.getAbsolutePath());

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}