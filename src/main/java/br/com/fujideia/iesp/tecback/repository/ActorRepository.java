package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
