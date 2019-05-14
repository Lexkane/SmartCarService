package ua.ita.smartcarservice.service.files;

import ua.ita.smartcarservice.dto.files.AvatarDto;

public interface AvatarService {

    void addAvatarToUser(AvatarDto avatarDto);

    AvatarDto getAvatarDtoByUserId(Long userId);

}
