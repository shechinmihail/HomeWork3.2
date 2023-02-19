package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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

    @GetMapping(path = "/count")  //GET http://localhost:8080/student/count
    public ResponseEntity<Integer> getStudentsCount() {
        return ResponseEntity.ok(studentService.getCount());
    }

    @GetMapping(path = "/averageage")  //GET http://localhost:8080/student/average-age
    public ResponseEntity<Double> getStudentsAverageAge() {
        return ResponseEntity.ok(studentService.gatAverageAge());
    }

    @GetMapping(path = "/lastfivestudents")  //GET http://localhost:8080/student/last-five-students
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

    @GetMapping(path = "/thenamebeginswithA")  //GET http://localhost:8080/student/the-name-begins-with-A
    public ResponseEntity<Stream<String>> getStudentsWhomNamesBeginsWithA() {
        return ResponseEntity.ok(studentService.getWithNamesBeginsWithA());
    }

    @GetMapping(path = "/findStudentAverageAge")  //GET http://localhost:8080/student/findStudentAverageAge
    public ResponseEntity<Double> findStudentAverageAge() {
        return ResponseEntity.ok(studentService.findStudentAverageAge());
    }
}
