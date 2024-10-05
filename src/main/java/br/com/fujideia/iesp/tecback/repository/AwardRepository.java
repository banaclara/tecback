package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
    @Query("SELECT a.name, a.category FROM Award a WHERE a.awardYear = :awardYear")
    List<Award> findWinnersByYear(@Param("awardYear") Integer awardYear);

    @Query("SELECT a FROM Award a WHERE a.winner = :film")
    List<Award> findAwardsWonByFilm(@Param("film") Long film);
}
