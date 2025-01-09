package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultiesRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultiesRepository) {
        this.facultiesRepository = facultiesRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Метод создания факультета");
        return facultiesRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Метод поиска факультета");
        return facultiesRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public Faculty updateFaculty(long id, Faculty facultyForUpdate) {
        logger.info("Метод изменения факультета");
        if (!facultiesRepository.existsById(id)) {
            throw new FacultyNotFoundException(id);
        }
        facultyForUpdate.setId(id);
        return facultiesRepository.save(facultyForUpdate);
    }


    public Faculty deleteFaculty(long id) {
        logger.info("Метод удаления факультета");
        Faculty faculty = facultiesRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultiesRepository.delete(faculty);
        return faculty;
    }


    public List<Faculty> findAllByColor(String color) {
        logger.info("Метод поиска по цвету факультета");
        return facultiesRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }


    public List<Faculty> getFacultyByColorOrName(String color, String name) {
        logger.info("Метод поиска по цвету или названию факультета");
        return facultiesRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
}
