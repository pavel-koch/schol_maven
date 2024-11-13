package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    private final HashMap<Long, Student> studentsRepository = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        studentsRepository.put(lastId, student);
        return student;
    }

    public Student findStudent (long id) {
        return studentsRepository.get(id);
    }


    public Student editStudent(Student student) {
        studentsRepository.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent (long id) {
        return studentsRepository.remove(id);
    }

    public List<Student> findAllByAge(int age) {
        return studentsRepository.values().stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

}
