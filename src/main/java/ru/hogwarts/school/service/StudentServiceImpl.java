package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Вызван метод создания студента");
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Вызван метод поиска студента по id");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Вызван метод для редактирования студента");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Вызван метод для удаления студента");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        logger.info("Вызван метод для получения списка всех студентов, зарегистрированных в базе данных.");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> findByNameAndAge(String name, int age) {
        logger.info("Вызван метод поиска учащихся, чье имя и возраст совпадают " + name + " и " + age);
        return studentRepository.findByNameIgnoreCaseAndAge(name, age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int age1, int age2) {
        logger.info("Вызван метод поиска учащихся, возраст которых находится в диапазоне от " + age1 + " до " + age2);
        return studentRepository.findByAgeBetween(age1, age2);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("Вызван метод для поиска факультета по id студента");
        return facultyRepository.findByStudentsId(id);
    }

    public Integer getCount() {
        logger.info("Вызван метод подсчета студентов в базе данных");
        return studentRepository.getCount();
    }

    public Double gatAverageAge() {
        logger.info("Вызван метод подсчета среднего возраста учащихся");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Вызван метод для отображения последних пяти студентов в базе данных");
        return studentRepository.getLastFiveStudents();
    }
}
