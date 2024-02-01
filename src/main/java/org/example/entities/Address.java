package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "addresses")
@Getter @Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_gen_id", sequenceName = "address_seq", allocationSize = 1)
public class Address extends BaseEntityId{
    @Column(nullable = false, unique = true)
    private String country;
    @OneToOne(cascade = {DETACH})
    private Company company;

    @OneToOne
    private Programmer programmer;


    public Address(String country) {
        this.country = country;
    }
}
