package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    @Query(value = "SELECT faculty.* FROM faculty, student \n" +
            "where student.faculty_id = faculty.id\n" +
            "and student.id = :id", nativeQuery = true)
    Faculty findByStudentId(long id);
}
