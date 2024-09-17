package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Award;
import br.com.fujideia.iesp.tecback.model.AwardCategory;
import br.com.fujideia.iesp.tecback.model.Soundtrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
    @Query("SELECT a.name, a.category FROM Award a WHERE a.year = :year")
    List<Award> findWinnersByYear(@Param("year") Integer year);

    @Query("SELECT a FROM Award a WHERE a.category = :category AND a.winner = :filmTitle")
    List<Award> findAwardsWonByFilm(@Param("category") AwardCategory category, @Param("filmTitle") String filmTitle);
}
