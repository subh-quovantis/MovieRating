package com.example.movieratingpoc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Movie {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String movieName;
    private String genre;
    @JsonManagedReference

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Rating> rating=new HashSet<>() ;

}
