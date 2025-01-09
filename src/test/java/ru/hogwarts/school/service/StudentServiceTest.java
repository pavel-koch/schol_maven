package ru.hogwarts.school.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("Добавление студента")
    void createStudent() {
        Student expected = new Student("test", 25);

        //test
        Student actual = studentService.createStudent(expected);

        //check
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Посик студента")
    void findStudent() {
        Student expected = new Student("test", 25);
        Student saveStudent = studentService.createStudent(expected);

        //test
        Student actual = studentService.findStudent(saveStudent.getId());

        //check
        assertEquals(actual, saveStudent);
    }

    @Test
    @DisplayName("Изменение студента")
    void editStudent() {
        Student student = new Student("test", 25);
        Student saveStudent = studentService.createStudent(student);
        Student expected = new Student("test", 22);

        //test
        Student actual = studentService.updateStudent(saveStudent.getId(), expected);

        //check
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Удаление студента")
    void deleteStudent() {
        Student expected = new Student("test", 25);
        Student saveStudent = studentService.createStudent(expected);

        //test
        Student actual = studentService.deleteStudent(saveStudent.getId());

        //check
        assertEquals(actual, saveStudent);
    }

    @Test
    @DisplayName("Поиск студентов по возрасту")
    void findAllByAge() {
        int age = 16;
        Student student = new Student("test", 25);
        Student expected = new Student("test", age);
        Student expected2 = new Student("test2", age);
        studentService.createStudent(expected);
        studentService.createStudent(expected2);
        studentService.createStudent(student);

        //test
        List<Student> actual = studentService.findAllByAge(age);

        //check
        assertThat(actual).containsAll(List.of(expected, expected2));

    }
}