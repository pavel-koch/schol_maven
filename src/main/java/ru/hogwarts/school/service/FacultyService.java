package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultiesRepository;

    public FacultyService(FacultyRepository facultiesRepository) {
        this.facultiesRepository = facultiesRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultiesRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultiesRepository.findById(id).orElseThrow(()-> new FacultyNotFoundException(id));
    }

    public Faculty updateFaculty(long id, Faculty facultyForUpdate) {
        if (!facultiesRepository.existsById(id)) {
            throw new FacultyNotFoundException(id);
        }
        facultyForUpdate.setId(id);
        return facultiesRepository.save(facultyForUpdate);
    }


    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultiesRepository.findById(id).orElseThrow(()-> new FacultyNotFoundException(id));
        facultiesRepository.delete(faculty);
        return faculty;
    }


    public List<Faculty> findAllByColor(String color) {
        return facultiesRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }


    public List<Faculty> getFacultyByColorOrName(String color, String name) {
        return facultiesRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
}
