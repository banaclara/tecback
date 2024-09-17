package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Award;
import br.com.fujideia.iesp.tecback.model.AwardCategory;
import br.com.fujideia.iesp.tecback.model.dto.AwardDTO;
import br.com.fujideia.iesp.tecback.repository.AwardRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardService {
    private final AwardRepository awardRepository;

    public AwardDTO andTheAwardGoesTo(AwardDTO awardDTO) {
        Award award = Converter.convertToEntity(awardDTO);
        return Converter.convertToDTO(awardRepository.save(award));
    }

    public List<AwardDTO> winnersOfTheYear(Integer year) {
        return awardRepository.findWinnersByYear(year).stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

//    public List<AwardDTO> listAwardsWon(Long filmId) {}
}
