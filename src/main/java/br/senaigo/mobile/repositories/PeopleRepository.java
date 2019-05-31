package br.senaigo.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senaigo.mobile.entities.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
