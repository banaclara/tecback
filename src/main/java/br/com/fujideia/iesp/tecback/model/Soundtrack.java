package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Soundtrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String composer;

    @OneToMany(mappedBy = "soundtrack")
    private List<Track> tracks;

    @OneToOne(mappedBy = "soundtrack")
    private Film film;

    @Transient
    public String totalDuration() {
        int total = tracks.stream().mapToInt(Track::getDuration).sum();
        int hours = total / 3600;
        int min = (total % 3600) / 60;
        int sec = total % 60;
        return String.format("%02d:%02d:%02d", hours, min, sec);
    }
}
