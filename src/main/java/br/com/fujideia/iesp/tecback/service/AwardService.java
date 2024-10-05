package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Award;
import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.dto.AwardDTO;
import br.com.fujideia.iesp.tecback.repository.AwardRepository;
import br.com.fujideia.iesp.tecback.repository.FilmRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardService {
    private final AwardRepository awardRepository;
    private final FilmRepository filmRepository;

    public AwardDTO andTheAwardGoesTo(AwardDTO awardDTO) {
        Award award = Converter.convertToEntity(awardDTO);
        Film film = filmRepository.findById(awardDTO.getFilm().getId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found"));
        award.setFilm(film);
        return Converter.convertToDTO(awardRepository.save(award));
    }

    public List<AwardDTO> winnersOfTheYear(Integer year) {
        return awardRepository.findWinnersByYear(year).stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AwardDTO> listAwardsWonByFilm(Long filmId) {
        return awardRepository.findAwardsWonByFilm(filmId).stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }
}
