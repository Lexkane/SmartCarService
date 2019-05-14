package ua.ita.smartcarservice.service.impl.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.files.AvatarDto;
import ua.ita.smartcarservice.entity.files.AvatarEntity;
import ua.ita.smartcarservice.repository.files.AvatarRepository;
import ua.ita.smartcarservice.service.files.AvatarService;
import ua.ita.smartcarservice.service.UserService;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    UserService userService;

    @Override
    public void addAvatarToUser(AvatarDto avatarDto) {
        avatarRepository.save(dtoToEntity(avatarDto));
    }

    public AvatarEntity dtoToEntity(AvatarDto avatarDto) {
        return new AvatarEntity(
                userService.findById(avatarDto.getUserId()),
                avatarDto.getFileUrl(),
                avatarDto.getFilePath());
    }

    public AvatarDto entityToDto(AvatarEntity avatarEntity) {
        return new AvatarDto(
                avatarEntity.getUserId().getId(),
                avatarEntity.getFileUrl(),
                avatarEntity.getFilePath());
    }

    @Override
    public AvatarDto getAvatarDtoByUserId(Long userId) {
        return entityToDto(avatarRepository.getByUserId(userId));
    }
}
