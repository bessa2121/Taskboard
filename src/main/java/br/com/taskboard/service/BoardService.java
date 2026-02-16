package br.com.taskboard.service;

import br.com.taskboard.model.*;
import br.com.taskboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(String name) {

        Board board = new Board();
        board.setName(name);

        BoardColumn initial = createColumn("Inicial", 1, ColumnType.INITIAL, board);
        BoardColumn pending = createColumn("Pendente", 2, ColumnType.PENDING, board);
        BoardColumn done = createColumn("Final", 3, ColumnType.FINAL, board);
        BoardColumn canceled = createColumn("Cancelado", 4, ColumnType.CANCELED, board);

        board.getColumns().add(initial);
        board.getColumns().add(pending);
        board.getColumns().add(done);
        board.getColumns().add(canceled);

        return boardRepository.save(board);
    }

    private BoardColumn createColumn(String name, int order, ColumnType type, Board board) {
        BoardColumn column = new BoardColumn();
        column.setName(name);
        column.setOrderPosition(order);
        column.setType(type);
        column.setBoard(board);
        return column;
    }

    public List<Board> listBoards() {
        return boardRepository.findAll();
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board não encontrado"));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
