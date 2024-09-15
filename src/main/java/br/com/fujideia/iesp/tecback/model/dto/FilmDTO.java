package br.com.fujideia.iesp.tecback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private Long id;
    private String title;
    private Integer releaseYear;
    private DirectorDTO director;
    private List<ActorDTO> actors;
    private List<GenreDTO> genres;
    private List<ProducerDTO> producers;
    private SoundtrackDTO soundtrack;
}
