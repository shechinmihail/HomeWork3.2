package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Вызван метод создания факультета");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("Вызван метод поиска факультета по id");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty changeFaculty(Faculty faculty) {
        logger.info("Вызван метод для редактирования факультета");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Вызван метод для удаления факультета");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        logger.info("Вызван метод для получения списка всех факультетов");
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findByNameOrColor(String name, String color) {
        logger.info("Вызван метод для получения факультета по его названию или цвету");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> findStudentsById(long id) {
        logger.info("Вызван метод для получения списка студентов факультета по id факультета.");
        return studentRepository.findStudentsByFacultyId(id);
    }


}
