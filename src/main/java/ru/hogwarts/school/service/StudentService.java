package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentsRepository;

    private final FacultyRepository facultiesRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentsRepository, FacultyRepository facultiesRepository) {
        this.studentsRepository = studentsRepository;
        this.facultiesRepository = facultiesRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Метод создания студента");
        logger.debug("Создание студента с именем: " + student.getName());
        return studentsRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Метод поиска студента по id");
        return studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }


    public Student updateStudent(long id, Student studentForUpdate) {
        logger.info("Метод для обновления данных студента");
        if (!studentsRepository.existsById(id)) {
            logger.warn("Нет такого студента по id: " + id);
            throw new StudentNotFoundException(id);
        }
        logger.debug("Обновление студента: " + studentForUpdate.getName() + " с id: " + id);
        studentForUpdate.setId(id);
        return studentsRepository.save(studentForUpdate);
    }

    public Student deleteStudent(long id) {
        logger.info("Метод удаления студента");
        Student student = studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentsRepository.delete(student);
        logger.debug("Дебаг удаления студента по id " + id);
        return student;
    }

    public List<Student> findAllByAge(int age) {
        logger.info("Сортируем студентов по возрасту");
        return studentsRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

    public List<Student> findByAgeBetween(int ageMin, int ageMax) {
        logger.info("Сортируем студентов по возрасту между минимумом и максимом");
        return studentsRepository.findByAgeBetween(ageMin, ageMax);
    }

    public List<Student> findByFacultyId(long id) {
        logger.info("Поиск студента по факультету");
        return studentsRepository.findByFacultyId(id);
    }

    public int countStudent() {
        logger.info("Находим количество студентов");
        return studentsRepository.countStudent();
    }

    public long avgAgeStudent() {
        logger.info("Находим средний возраст студентов");
        return studentsRepository.avgAgeStudent();
    }

    public List<Student> getLastFiveStudent() {
        logger.info("Находим пять последних студентов");
        return studentsRepository.getLastFiveStudent();
    }

    public List<String> findAllByBeginName(String beginName) {
        logger.info("Сортируем студентов по первой букве имени");
        return studentsRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.startsWith(beginName.toUpperCase()) || name.startsWith(beginName.toLowerCase()))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAgeStudents() {
        logger.info("Получаем средний возраст студентов");
        return studentsRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public void getPrintParallel() {
        logger.info("Получаем имена первых шести студентов");
        List<Student> students = studentsRepository.findAll();

        if (students.size() < 6) {
            System.out.println("Слишком мало студентов");
        }
        printInStream(students, 0, 2);
        Thread thread = new Thread(() -> printInStream(students, 2, 2));
        Thread thread2 = new Thread(() -> printInStream(students, 4, 2));

        thread.start();
        thread2.start();

    }

    private void printInStream(Collection<Student> students, int offset, int limit) {
        System.out.println("Выполняется тред: %s".formatted(Thread.currentThread().getName()));
        students.stream()
                .skip(offset)
                .limit(limit)
                .forEach(System.out::println);
    }

    private synchronized void printInStreamSynchronized(Collection<Student> students, int offset, int limit) {
        printInStream(students, offset, limit);
    }

    public void getPrintSynchronized() {
        logger.info("Получаем имена первых шести студентов");
        List<Student> students = studentsRepository.findAll();

        if (students.size() < 6) {
            System.out.println("Слишком мало студентов");
        }
        printInStreamSynchronized(students, 0, 2);
        Thread thread = new Thread(() -> printInStreamSynchronized(students, 2, 2));
        Thread thread2 = new Thread(() -> printInStreamSynchronized(students, 4, 2));

        thread.start();
        thread2.start();

    }

}

