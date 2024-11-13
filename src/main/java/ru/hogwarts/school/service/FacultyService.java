package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> facultiesRepository = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultiesRepository.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty (long id) {
        return facultiesRepository.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        facultiesRepository.put(faculty.getId(), faculty);
        return faculty;
    }


    public Faculty deleteFaculty (long id) {
        return facultiesRepository.remove(id);
    }


    public List<Faculty> findAllByColor(String color) {
        return facultiesRepository.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }
}
