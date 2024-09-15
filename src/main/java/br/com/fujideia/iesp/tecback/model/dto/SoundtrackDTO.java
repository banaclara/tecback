package br.com.fujideia.iesp.tecback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoundtrackDTO {
    private Long id;
    private String composer;
    private List<TrackDTO> tracks;
    private FilmDTO film;
    private String totalDuration;
}
