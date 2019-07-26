package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;
    private List<Course> courseList;

    @Before
    public void setUp() throws Exception {
        courseList = new ArrayList<>();

        Instructor i1 = new Instructor("Sally");
        i1.setInstructid(1);

        Instructor i2 = new Instructor("Lucy");
        i2.setInstructid(2);

        Instructor i3 = new Instructor("Charlie");
        i3.setInstructid(3);

        Course c1 = new Course("Data Science", i1);
        c1.setCourseid(11);

        Course c2 = new Course("JavaScript", i1);
        c1.setCourseid(12);

        Course c3 = new Course("Node.js", i1);
        c1.setCourseid(13);

        Course c4 = new Course("Java Back End", i2);
        c1.setCourseid(14);

        Course c5 = new Course("Mobile IOS", i2);
        c1.setCourseid(15);

        Course c6 = new Course("Mobile Android", i3);
        c6.setCourseid(16);

        Student s1 = new Student("John");
        s1.setStudid(21);
        s1.getCourses().add(c1);
        s1.getCourses().add(c4);

        Student s2 = new Student("Julian");
        s2.setStudid(22);
        s2.getCourses().add(c2);

        Student s3 = new Student("Mary");
        s3.setStudid(23);
        s3.getCourses().add(c3);
        s3.getCourses().add(c1);
        s3.getCourses().add(c4);

        c1.getStudents().add(s1);
        c4.getStudents().add(s1);
        c2.getStudents().add(s2);
        c3.getStudents().add(s3);
        c1.getStudents().add(s3);
        c4.getStudents().add(s3);

        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        courseList.add(c6);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllCourses() throws Exception {
//        courserepos.findAll().iterator().forEachRemaining(list::add);
        String apiUrl = "/courses/courses/all";
        Mockito.when(courseService.findAll()).thenReturn((ArrayList<Course>) courseList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);
        assertEquals("Rest Api Returns List", er, tr);
    }
}