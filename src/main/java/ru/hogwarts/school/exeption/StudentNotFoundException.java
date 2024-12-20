package ru.hogwarts.school.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Студент не найден")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(long id) {
        super("Студент: %s не найден".formatted(id));
    }
}

