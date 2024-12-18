package ru.hogwarts.school.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Факультет не найден")
public class FacultyNotFoudnExeption extends RuntimeException {
    public FacultyNotFoudnExeption(long id) {
        super("Факультет: %s не найден".formatted(id));
    }
}
