package br.com.taskboard.repository;

import br.com.taskboard.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
}