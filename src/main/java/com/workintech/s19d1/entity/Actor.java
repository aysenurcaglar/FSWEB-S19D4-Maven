package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "actor")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;

    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }
}
