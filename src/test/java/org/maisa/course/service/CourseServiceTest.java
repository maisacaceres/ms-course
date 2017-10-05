package org.maisa.course.service;

import org.junit.Before;
import org.junit.Test;
import org.maisa.course.domain.Course;
import org.maisa.course.exception.RecordNotFoundException;
import org.maisa.course.repository.CourseRepository;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;


    private UUID courseId;
    private Course course;

    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        courseId = UUID.randomUUID();

        course = createCourse();

    }

    private Course createCourse() {

        Course course = new Course();
        course.setId(courseId);
        course.setName("Curso teste");
        course.setDescription("Meu primeiro curso");
        return course;

    }

    @Test
    public void findByIdTest() {

        Course c = mock(Course.class);
        when(courseRepository.findOne(courseId)).thenReturn(c);
        courseService.findById(courseId);
        verify(courseRepository).findOne(any());

    }

    @Test(expected = RecordNotFoundException.class)
    public void findByIdTestNotFound() {

        when(courseRepository.findOne(courseId)).thenReturn(null);
        courseService.findById(courseId);

    }

    @Test
    public void createTest() {

        Course c = mock(Course.class);

        courseService.create(c);

        verify(c).setId(any());
        verify(courseRepository).save(Matchers.<Course>any());

    }

    @Test
    public void updateTest() {

        Course c = mock(Course.class);

        when(courseRepository.findOne(any())).thenReturn(c);

        courseService.update(c);

        verify(c).setName(any());
        verify(c).setDescription(any());
        verify(courseRepository).save(Matchers.<Course>any());

    }

}
