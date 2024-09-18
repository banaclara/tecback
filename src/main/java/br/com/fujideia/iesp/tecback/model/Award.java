package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer awardYear;

    @Enumerated(EnumType.STRING)
    private AwardCategory category;

    private String winner;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
