package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.Producer;
import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProducerDTO;
import br.com.fujideia.iesp.tecback.repository.FilmRepository;
import br.com.fujideia.iesp.tecback.repository.ProducerRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final FilmRepository filmRepository;

    public List<ProducerDTO> listProducers() {
        return producerRepository.findAll().stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FilmDTO> listFilmsProduced(Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        return producer.getProducedFilms()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProducerDTO> searchById(Long id) {
        return producerRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public List<ProducerDTO> searchByName(String name) {
        List<Producer> producers = producerRepository.findByName(name);
        return producers.stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProducerDTO createProducer(ProducerDTO producerDTO) {
        Producer producer = Converter.convertToEntity(producerDTO);
        return Converter.convertToDTO(producerRepository.save(producer));
    }

    public ProducerDTO addFilmToProducer(Long producerId, Long filmId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        if (!producer.getProducedFilms().contains(film)) {
            producer.getProducedFilms().add(film);
            producerRepository.save(producer);
        }
        return Converter.convertToDTO(producer);
    }

    public Optional<ProducerDTO> updateProducer(Long id, ProducerDTO producerDTO) {
        return producerRepository.findById(id).map(producer -> {
            producer.setName(producerDTO.getName());
            producer.setAge(producerDTO.getAge());
            producer.setNationality(producerDTO.getNationality());
            producer.setProducedFilms(producerDTO.getProducedFilms().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
            return Converter.convertToDTO(producerRepository.save(producer));
        });
    }

    public boolean deleteProducer(Long id) {
        if (producerRepository.existsById(id)) {
            producerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
