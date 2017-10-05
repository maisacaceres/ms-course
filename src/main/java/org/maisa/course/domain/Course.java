package org.maisa.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    private UUID id;

    @NotNull
    @Size(min = 3, message = "O curso deve ter pelo menos 3 caracteres")
    private String name;

    private String description;
}
