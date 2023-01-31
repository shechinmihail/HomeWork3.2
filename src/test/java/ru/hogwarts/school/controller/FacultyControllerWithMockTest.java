package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private ObjectMapper objectMapper;

    @Test
    void creatFacultyTest() throws Exception {
        final Long id = 13L;
        final String name = "Счастливые";
        final String color = "Серо-буро-малиновый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        when(facultyRepository.existsById(id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void findFacultyTest() throws Exception {
        final Long id = 13L;
        final String name = "Счастливые";
        final String color = "Серо-буро-малиновый";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    public void changeFacultyTest() throws Exception {
        Long id = 13L;
        String oldName = "Счастливые";
        String oldColor = "Серо-буро-малиновый";

        String newName = "Перцы";
        String newColor = "Малиновый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", newName);
        facultyObject.put("color", newColor);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(oldName);
        faculty.setColor(oldColor);

        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setId(id);
        updatedFaculty.setName(newName);
        updatedFaculty.setColor(newColor);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(updatedFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.color").value(newColor));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        Long id = 13L;
        String name = "Счастливые";
        String color = "Серо-буро-малиновый";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.getById(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(facultyRepository, atLeastOnce()).deleteById(id);
    }

    @Test
    public void getAllFacultiesTest() throws Exception {

        final Long id = 13L;
        final String name = "Счастливые";
        final String color = "Серо-буро-малиновый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .content(facultyObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all")
                        .content(facultyObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

//    @Test
//    public void findFacultyByNameOrColorTest() throws Exception {
//        Long id = 3L;
//        String name = "SuperPuper";
//
//        Long id1 = 13L;
//        String name1 = "Счастливые";
//
//        Long id2 = 7L;
//        String name2 = "Перцы";
//
//        String color = "Red";
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        Faculty faculty1 = new Faculty();
//        faculty1.setId(id1);
//        faculty1.setName(name1);
//        faculty1.setColor(color);
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setId(id2);
//        faculty2.setName(name2);
//        faculty2.setColor(color);
//
//
//        when(facultyRepository.
//                findByNameIgnoreCaseOrColorIgnoreCase(name, color)).thenReturn((Faculty) Set.of(faculty, faculty1, faculty2));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty" + color)
//                        .queryParam("color", color)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty, faculty1, faculty2))));
//    }
//
//    @Test
//    public void getStudentsByFaculty() throws Exception {
//        final Long id = 13L;
//        final String name = "Счастливые";
//        final String color = "Серо-буро-малиновый";
//
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("id", id);
//        facultyObject.put("name", name);
//        facultyObject.put("color", color);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(id);
//        faculty.setName(name);
//        faculty.setColor(color);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/{id}/students/" + id)
//                        .content(facultyObject.toString())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.color").value(color));
//    }
}