package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Soundtrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoundtrackRepository extends JpaRepository<Soundtrack, Long> {
    @Query("SELECT s FROM Soundtrack s WHERE s.composer LIKE %:composer%")
    List<Soundtrack> findByComposer(@Param("composer") String composer);
}
