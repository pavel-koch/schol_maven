package ru.hogwarts.school.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Аватар не найден")
public class AvatarNotFoundException extends RuntimeException {
    public AvatarNotFoundException(long id) {
        super("Аватар студента: %s не найден".formatted(id));
    }
}
