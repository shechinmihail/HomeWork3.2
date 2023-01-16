package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class StudentServiceImpl implements StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }

    @Override
    public Collection<Student> filterForAge(int age) {
        Collection<Student> tmp = new ArrayList<>();
        for (long i = 1; i <= students.size(); i++) {
            if (students.get(i) == null) {
                i++;
            }
            if (students.get(i).getAge() == age) {
                tmp.add(students.get(i));
            }
        }
        return Collections.unmodifiableCollection(tmp);
    }
}
