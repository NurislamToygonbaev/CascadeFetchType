package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter @Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_gen_id", sequenceName = "company_seq", allocationSize = 1)
public class Company extends BaseEntityId{
    @Column(name = "company_name", nullable = false, unique = true)
    private String name;
    @OneToOne(cascade = {REMOVE})
    @ToString.Exclude
    private Address address;

    @OneToMany(mappedBy = "company", cascade = {PERSIST, REMOVE})
    private List<Project> projects;
    public Company(String name) {
        this.name = name;
    }
}
