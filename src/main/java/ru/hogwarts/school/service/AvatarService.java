package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.Collection;

public interface AvatarService {
    public void upLoadAvatar(Long studentId, MultipartFile file) throws IOException;
    public Avatar downLoadAvatar(Long id);
    public Collection<Avatar> getAllAvatars();
    public Avatar editAvatar(Avatar avatar);
    public void deleteAvatar(Long id);
}
