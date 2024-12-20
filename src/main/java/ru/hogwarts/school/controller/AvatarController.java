package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@RequestParam("studentId") long studentId,
                            @RequestBody MultipartFile multipartFile) throws IOException {
        avatarService.uploadImage(studentId, multipartFile);
    }

    @GetMapping(path = "/get/from-db", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAvatarFromDB(@RequestParam("studentId") long studentId) {
        return avatarService.getAvatarFromDB(studentId);
    }

    @GetMapping(path = "/get/from-local", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatarFromLocal(@RequestParam("studentId") long studentId) {
        Avatar avatar = avatarService.getAvatarFromLocal(studentId);
        return ResponseEntity.status(HttpStatus.OK).contentType().body(avtar);
    }

}
