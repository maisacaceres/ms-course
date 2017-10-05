package org.maisa.course.repository;

import org.maisa.course.domain.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CourseRepository extends PagingAndSortingRepository<Course, UUID> {
}
