package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    @Query("SELECT f FROM Producer f WHERE f.name LIKE %:name%")
    List<Producer> findByName(@Param("name") String name);
}
