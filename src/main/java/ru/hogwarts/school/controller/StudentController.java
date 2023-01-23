package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping //POST http://localhost:8080/student
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping(path = "{id}") //GET http://localhost:8080/student/2
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping //PUT http://localhost:8080/student
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping(path = "{id}")  //DELETE http://localhost:8080/student/2
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "all")  //GET http://localhost:8080/student/all
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(path = "/age-between") //GET http://localhost:8080/student/age-between?age1=11&age2=12
    public ResponseEntity<Collection<Student>> getStudentsByAgeIsBetween(@RequestParam int age1,
                                                                         @RequestParam int age2) {
        return ResponseEntity.ok(studentService.findByAgeBetween(age1, age2));
    }

    @GetMapping  //GET http://localhost:8080/student
    public ResponseEntity<Collection<Student>> findStudent(@RequestParam(required = false) Integer age,
                                                           @RequestParam(required = false) String name) {
        return ResponseEntity.ok(studentService.findByNameAndAge(name, age));
    }

    @GetMapping(path = "{id}/faculty")//GET http://localhost:8080/student/{id}/faculty
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findFaculty(id));
    }
}
