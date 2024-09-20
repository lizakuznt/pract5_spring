package com.example.pract_5;

import com.example.pract_5.controller.StudentApiController;
import com.example.pract_5.model.StudentModel;
import com.example.pract_5.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentApiController.class)
public class StudentsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentModel student = new StudentModel();
        student.setId(1L);
        student.setName("Дмитрий Позов");
        student.setEmail("dimapozov@bk.com");

        when(studentService.createStudent(any(StudentModel.class))).thenReturn(student);

        mockMvc.perform(post("/v1/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Дмитрий Позов\",\"email\":\"dimapozov@bk.com\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Дмитрий Позов"))
                .andExpect(jsonPath("$.email").value("dimapozov@bk.com"));
    }
}
