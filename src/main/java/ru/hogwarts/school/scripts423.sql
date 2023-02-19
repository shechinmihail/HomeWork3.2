SELECT student.name, student.age, faculty.name
FROM student
         LEFT JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age, a.data , a.file_path
FROM student
         INNER JOIN avatar a ON student.id = a.student_id