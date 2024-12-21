package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hogwarts.school.exeption.FacultyNotFoudnExeption;
import ru.hogwarts.school.exeption.StudentNotFoudnExeption;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentsRepository;

    private final FacultyRepository facultiesRepository;

    public StudentService(StudentRepository studentsRepository, FacultyRepository facultiesRepository) {
        this.studentsRepository = studentsRepository;
        this.facultiesRepository = facultiesRepository;
    }

    public Student createStudent(Student student) {
        return studentsRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoudnExeption(id));
    }


    public Student updateStudent(long id, Student studentForUpdate) {
        if (!studentsRepository.existsById(id)) {
            throw new StudentNotFoudnExeption(id);
        }
        studentForUpdate.setId(id);
        return studentsRepository.save(studentForUpdate);
    }

    public Student deleteStudent(long id) {
        Student student = studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoudnExeption(id));
        studentsRepository.delete(student);
        return student;
    }

    public List<Student> findAllByAge(int age) {
        return studentsRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

    public List<Student> findByAgeBetween(int ageMin, int ageMax) {
        return studentsRepository.findByAgeBetween(ageMin, ageMax);
    }

    public List<Student> findByFacultyId(long id) {
        return studentsRepository.findByFacultyId(id);
    }

}

