package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping //POST http://localhost:8080/faculty
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);
    }

    @GetMapping(path = "{id}") //GET http://localhost:8080/faculty
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping //PUT http://localhost:8080/faculty/2
    public ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.changeFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping(path = "{id}") //DELETE http://localhost:8080/faculty/2
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "all")  //GET http://localhost:8080/faculty/all
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping  //GET http://localhost:8080/faculty?nameOrColor=name-or-color
    public ResponseEntity<Faculty> findFacultyByNameOrColor(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findByNameOrColor(name, color));
    }

    @GetMapping(path = "{id}/students") //GET http://localhost:8080/faculty/{id}/students
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.findStudentsById(id));
    }
}