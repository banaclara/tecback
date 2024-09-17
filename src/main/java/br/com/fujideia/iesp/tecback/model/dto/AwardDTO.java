package br.com.fujideia.iesp.tecback.model.dto;

import br.com.fujideia.iesp.tecback.model.AwardCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardDTO {
    private Long id;
    private String name;
    private int year;
    private AwardCategory category;
    private String winner;
}
