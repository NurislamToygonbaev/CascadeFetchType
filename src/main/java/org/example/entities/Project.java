package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "projects")
@SequenceGenerator(name = "base_gen_id", sequenceName = "project_seq", allocationSize = 1)
@Getter @Setter
@NoArgsConstructor
@ToString
public class Project extends BaseEntityId{
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @ToString.Exclude
    private Company company;

    @ManyToMany(cascade = {DETACH})
    private List<Programmer> programmers;

    public Project(String title) {
        this.title = title;
    }
}
