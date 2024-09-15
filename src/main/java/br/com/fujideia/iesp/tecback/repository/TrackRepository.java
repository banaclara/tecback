package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
}
