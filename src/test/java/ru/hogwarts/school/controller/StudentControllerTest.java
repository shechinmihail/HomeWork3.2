package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void addStudentTest() throws Exception {
        Student student = new Student();
        student.setId(2L);
        student.setName("Жорик");
        student.setAge(27);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    void findStudentTest() throws Exception {
        int id = 1;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class)).isNotNull();
    }

    @Test
    void editStudentTest() throws Exception {
        Student student = new Student();
        student.setId(2L);
        student.setName("Эдриан");
        student.setAge(18);

        restTemplate
                .put("http://localhost:" + port + "/student", student, String.class);

        Assertions
                .assertThat(this.restTemplate
                        .getForObject("http://localhost:" + port + "/student/2", String.class)).isNotEqualTo(student);
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setId(2L);
        student.setName("Михаил");
        student.setAge(28);

        restTemplate
                .put("http://localhost:" + port + "/student", student, String.class);
        restTemplate
                .delete("http://localhost:" + port + "/student/2", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/2", String.class));
    }

    @Test
    void getAllTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/all", String.class))
                .isNotNull();
    }

    @Test
    void findByNameAndAgeTest() throws Exception {
        String name = "Маргарита";
        long age = 27;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + age + name, String.class)).isNotNull();
    }

    @Test
    void getStudentByAgeBetweenTest() throws Exception {
        long age1 = 18;
        long age2 = 27;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age-between" + age1 + age2, String.class))
                .isNotNull();
    }

    @Test
    void getFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(7L);
        faculty.setName("Fans is Harry Potter");
        faculty.setColor("Розовый");
        restTemplate.put("http://localhost:" + port + "/faculty", faculty, String.class);

        Student student = new Student();
        student.setId(2L);
        student.setName("Саша");
        student.setAge(19);
        student.setFaculty(faculty);
        restTemplate.put("http://localhost:" + port + "/student", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/2", String.class))
                .isNotNull();

    }
}