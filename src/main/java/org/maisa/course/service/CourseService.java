package org.maisa.course.service;

import lombok.extern.slf4j.Slf4j;
import org.maisa.course.domain.Course;
import org.maisa.course.exception.RecordNotFoundException;
import org.maisa.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Método responsável por buscar o curso por id
     * @param courseId: código do curso
     * @return o curso encontrado ou mensagem de que não foi possível encontrar o curso
     */
    public Course findById(UUID courseId) {

        log.info("Buscando por curso de id {}", courseId);
        Course course = courseRepository.findOne(courseId);

        if (null == course) {

            log.error("Curso com id {} não encontrado");
            throw new RecordNotFoundException("Curso não encontrado");
        }

        return course;

    }

    /**
     * Método responsável por buscar todos os cursos, de forma paginada
     * @param pageable: dados da página desejada
     * @return: uma lista com todos os cursos encontrados para a página informada
     */
    public Page<Course> findAll(Pageable pageable) {

        log.info("Pesquisando por todos os cursos da página {}", pageable);
        Page<Course> courses = courseRepository.findAll(pageable);

        if (null == courses) {

            log.error("Nenhum curso encontrado");
            throw new RecordNotFoundException("Nenhum curso encontrado");

        }

        return courses;
    }

    /**
     * Método responsável por criar um novo curso
     * @param course: dados do curso que será criado
     * @return: o curso criado com sucesso
     */
    public Course create(Course course) {

        log.info("Gerando um UUID para o curso");
        course.setId(UUID.randomUUID());

        log.info("Criando o novo curso");
        return courseRepository.save(course);

    }

    /**
     * Método responsável por alterar o curso
     * @param course: curso com os novos dados
     * @return: curso alterado
     */
    public Course update(Course course) {

        Course courseDB = findById(course.getId());

        log.info("Atualizando os novos dados para o curso");
        courseDB.setName(course.getName());
        courseDB.setDescription(course.getDescription());

        return courseRepository.save(courseDB);
    }

    /**
     * Método responsável por apagar um curso
     * @param courseId: código do curso que será apagado
     */
    public void delete(UUID courseId) {

        log.info("Apagando curso de id {}", courseId);
        courseRepository.delete(courseId);

    }
}
