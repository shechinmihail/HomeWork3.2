package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty creatFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty changeFaculty(Faculty faculty);

    Faculty deleteFaculty(long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> filterForColor(String color);
}
