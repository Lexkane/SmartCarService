package ua.ita.smartcarservice.entity.files;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name = "avatar")
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;
    @Column
    private String fileUrl;
    @Column
    private String filePath;

    public AvatarEntity() {
    }

    public AvatarEntity(UserEntity userId, String fileUrl, String filePath) {
        this.userId = userId;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
    }
}
