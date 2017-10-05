package org.maisa.course.controller;

import lombok.extern.slf4j.Slf4j;
import org.maisa.course.domain.Course;
import org.maisa.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public Course getById(@PathVariable final UUID courseId) {

        log.info("Request para buscar curso com id {}", courseId);
        return courseService.findById(courseId);

    }

    @GetMapping
    public Page<Course> findAll(Pageable page) {

        log.info("Request para pegar todos os cursos da página {}", page);
        return courseService.findAll(page);

    }

    @PostMapping
    public Course create(@Valid @RequestBody final Course course) {

        log.info("Request para criar um novo curso de nome {}", course.getName());
        return courseService.create(course);

    }

    @PutMapping("/{courseId}")
    public Course update(@PathVariable UUID courseId, @Valid @RequestBody Course course) {

        log.info("Request para alterar os dados do curso de id {}", courseId);
        course.setId(courseId);
        return courseService.update(course);

    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> delete(@PathVariable UUID courseId) {

        log.info("Request para apagar o curso {}", courseId);
        courseService.delete(courseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
