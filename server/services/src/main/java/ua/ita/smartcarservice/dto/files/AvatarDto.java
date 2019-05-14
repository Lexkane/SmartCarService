package ua.ita.smartcarservice.dto.files;

import lombok.Data;

@Data
public class AvatarDto {
    private Long userId;
    private String fileUrl;
    private String filePath;

    public AvatarDto(Long userId, String fileUrl, String filePath) {
        this.userId = userId;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
    }
}
