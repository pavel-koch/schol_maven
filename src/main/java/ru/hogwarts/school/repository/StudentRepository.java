package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int ageMin, int ageMax);

    List<Student> findByFacultyId(long id);

    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    int countStudent();

    @Query(value = "SELECT AVG (age) FROM student", nativeQuery = true)
    long avgAgeStudent();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudent();
}
