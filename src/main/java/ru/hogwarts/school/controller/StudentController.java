package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
     private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("{id}/delete")
    public Student deleteStudent (@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("{id}/get")
    public Student getStudentInfo(@PathVariable("id") Long id) {
        return studentService.findStudent(id);
    }

    @PutMapping("/edit")
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @GetMapping("/get/by-age")
    public List<Student> findAllByAge(@RequestParam("age") int age) {
        return studentService.findAllByAge(age);
    }
}
