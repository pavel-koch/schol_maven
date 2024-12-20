package ru.hogwarts.school.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
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
    public Student deleteStudent(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("{id}/get")
    public Student getStudentInfo(@PathVariable("id") long id) {
        return studentService.findStudent(id);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestParam("id") long id, @RequestBody Student studentForUpdate) {
        return studentService.updateStudent(id, studentForUpdate);
    }

    @GetMapping("/get/by-age")
    public List<Student> findAllByAge(@RequestParam("age") int age) {
        return studentService.findAllByAge(age);
    }

    @GetMapping("/get/by-age-between")
    public List<Student> findByAgeBetween(@RequestParam("ageMin") int ageMin,
                                          @RequestParam("ageMax") int ageMax) {
        return studentService.findByAgeBetween(ageMin, ageMax);
    }


    @GetMapping("/{idFaculty}/get/student")
    public List<Student> findByFacultyId(@PathVariable("idFaculty") long idFaculty) {
        return studentService.findByFacultyId(idFaculty);
    }

}
