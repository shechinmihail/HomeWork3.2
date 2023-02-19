package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty creatFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty changeFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> getAllFaculties();

    Faculty findByNameOrColor(String name, String color);

    Collection<Student> findStudentsById(long id);

    String findTheLongestFacultyName();
}
