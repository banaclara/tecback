package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.AwardCategory;
import br.com.fujideia.iesp.tecback.model.dto.AwardDTO;
import br.com.fujideia.iesp.tecback.service.AwardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awards")
@RequiredArgsConstructor
@Slf4j
public class AwardController {
    private final AwardService awardService;

    @PostMapping
    public ResponseEntity<AwardDTO> andTheAwardGoesTo(@RequestBody AwardDTO awardDTO) {
        AwardDTO award = awardService.andTheAwardGoesTo(awardDTO);
        return ResponseEntity.ok(award);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AwardDTO>> winnersOfTheYear(@RequestParam Integer year) {
        List<AwardDTO> winners = awardService.winnersOfTheYear(year);
        return ResponseEntity.ok(winners);
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<List<AwardDTO>> awardsWonByFilm(@PathVariable Long filmId) {
        List<AwardDTO> awards = awardService.listAwardsWonByFilm(filmId);
        return ResponseEntity.ok(awards);
    }
}
