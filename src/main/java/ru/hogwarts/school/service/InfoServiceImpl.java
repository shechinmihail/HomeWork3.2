package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Value("8080")
    private Integer serverPort;

    private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);
    private final StudentRepository studentRepository;

    private int index = 0;

    public InfoServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Integer portNumber() {
        return serverPort;
    }

    @Override
    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students);
        new Thread(() -> printStudents(students)).start();
        new Thread(() -> printStudents(students)).start();
    }

    private void printStudents(List<Student> students) {
        logger.info(students.get(index % students.size()).getName());
        index++;
    }

    private synchronized void studentsFromStreams(List<Student> students) {
        logger.info(students.get(index % students.size()).getName());
        index++;
    }

    @Override
    public void studentsFromStreams() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students);
        new Thread(() -> printStudents(students)).start();
        new Thread(() -> printStudents(students)).start();
    }

}
