package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/add")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }


    @DeleteMapping("{id}/delete")
    public Faculty deleteStudent (long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("{id}/get")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }


    @PutMapping("/edit")
    public Faculty editStudent(Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @GetMapping("/get/by-color")
    public List<Faculty> findAllByColor(@RequestParam("color") String color) {
        return facultyService.findAllByColor(color);
    }
}
