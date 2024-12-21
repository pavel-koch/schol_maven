package ru.hogwarts.school.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
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
    public Faculty deleteStudent(long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("{id}/get")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }


    @PutMapping("/update")
    public Faculty updateFaculty(@RequestParam("id") long id, @RequestBody Faculty facultyForUpdate) {
        return facultyService.updateFaculty(id, facultyForUpdate);
    }

    @GetMapping("/get/by-color")
    public List<Faculty> findAllByColor(@RequestParam("color") String color) {
        return facultyService.findAllByColor(color);
    }

    @GetMapping("/get/by-color-or-name")
    public List<Faculty> getFacultyByColorOrName(@RequestParam("color") String color,
                                                 @RequestParam("name") String name) {
        return facultyService.getFacultyByColorOrName(color, name);
    }
}
