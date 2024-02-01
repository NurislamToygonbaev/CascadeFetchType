package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "programmers")
@Getter @Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_gen_id", sequenceName = "programmer_id", allocationSize = 1)
public class Programmer extends BaseEntityId{
    @Column(name = "full_name", nullable = false, length = 500)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "programmers")
    @ToString.Exclude
    private List<Project> projects;

    @OneToOne(mappedBy = "programmer", cascade = {REMOVE, PERSIST})
    @ToString.Exclude
    private Address address;

    public Programmer(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
