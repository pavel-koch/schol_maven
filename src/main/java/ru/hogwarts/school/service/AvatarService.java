package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exeption.AvatarNotFoundException;
import ru.hogwarts.school.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
public class AvatarService {
    @Value("${path.dir}")
    private String pathDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public void uploadImage(long studentId, MultipartFile multipartFile) throws IOException {

        createDirectory();

        Path filePath = Path.of(pathDir, UUID.randomUUID()
                + "." + getExtension(multipartFile.getOriginalFilename()));
        createAvatar(studentId, multipartFile, filePath.toString());

        multipartFile.transferTo(filePath);
    }

    private String getExtension(String originalPath) {
        return originalPath.substring(originalPath.lastIndexOf(".") + 1);
    }


    public void createAvatar(long studentId, MultipartFile multipartFile, String filePath) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        avatarRepository.save(new Avatar(
                filePath,
                multipartFile.getSize(),
                multipartFile.getContentType(),
                multipartFile.getBytes(),
                student
        ));
    }


    private void createDirectory() throws IOException {
        Path path = Path.of(pathDir);
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
    }

    public Avatar getAvatarFromDB(long studentId) {
        chekExist(studentId);
        return avatarRepository.getByStudentId(studentId)
                .orElseThrow(() -> new AvatarNotFoundException(studentId));
    }

    public byte[] getAvatarFromLocal(long studentId) {
        chekExist(studentId);
        Avatar avatar = avatarRepository.getByStudentId(studentId)
                .orElseThrow(() -> new AvatarNotFoundException(studentId));
        String filePath = avatar.getFilePath();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            return bis.readAllBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException("Получение картинки не возможно: " + e.getMessage());
        }
    }

    private void chekExist(long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new StudentNotFoundException(studentId);
        }
    }

    public List<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

}
