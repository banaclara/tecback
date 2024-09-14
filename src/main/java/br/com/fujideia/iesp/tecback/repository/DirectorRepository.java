package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
