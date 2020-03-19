package be.belfius.sbjmscrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.belfius.sbjmscrud.model.JsonLine;

@Repository
//public interface JsonLineRepository extends JpaRepository<JsonLine, Long> {
 public interface JsonLineRepository extends CrudRepository<JsonLine, Long> {
}
