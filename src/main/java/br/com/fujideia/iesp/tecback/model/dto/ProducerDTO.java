package br.com.fujideia.iesp.tecback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDTO {
    private Long id;
    private String name;
    private int age;
    private String nationality;
    private List<FilmDTO> producedFilms;
}
