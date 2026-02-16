package br.com.taskboard.service;

import br.com.taskboard.model.*;
import br.com.taskboard.repository.CardRepository;
import br.com.taskboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    public Card createCard(Long boardId, String title, String description) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board não encontrado"));

        BoardColumn initial = board.getColumns().stream()
                .filter(c -> c.getType() == ColumnType.INITIAL)
                .findFirst()
                .orElseThrow();

        Card card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setCreatedAt(LocalDateTime.now());
        card.setBlocked(false);
        card.setColumn(initial);

        return cardRepository.save(card);
    }

    public void moveToNext(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card não encontrado"));

        if (card.isBlocked())
            throw new RuntimeException("Card está bloqueado");

        BoardColumn current = card.getColumn();
        Board board = current.getBoard();

        List<BoardColumn> columns = board.getColumns()
                .stream()
                .sorted(Comparator.comparingInt(BoardColumn::getOrderPosition))
                .toList();

        int index = columns.indexOf(current);

        if (current.getType() == ColumnType.FINAL)
            throw new RuntimeException("Card já está na coluna FINAL");

        BoardColumn next = columns.get(index + 1);

        if (next.getType() == ColumnType.CANCELED)
            throw new RuntimeException("Use a opção cancelar");

        card.setColumn(next);
        cardRepository.save(card);
    }

    public void cancelCard(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card não encontrado"));

        if (card.getColumn().getType() == ColumnType.FINAL)
            throw new RuntimeException("Card finalizado não pode ser cancelado");

        Board board = card.getColumn().getBoard();

        BoardColumn canceled = board.getColumns().stream()
                .filter(c -> c.getType() == ColumnType.CANCELED)
                .findFirst()
                .orElseThrow();

        card.setColumn(canceled);
        cardRepository.save(card);
    }

    public void blockCard(Long cardId, String reason) {

        if (reason == null || reason.isBlank())
            throw new RuntimeException("Motivo obrigatório");

        Card card = cardRepository.findById(cardId)
                .orElseThrow();

        card.setBlocked(true);
        card.setBlockReason(reason);

        cardRepository.save(card);
    }

    public void unblockCard(Long cardId, String reason) {

        if (reason == null || reason.isBlank())
            throw new RuntimeException("Motivo obrigatório");

        Card card = cardRepository.findById(cardId)
                .orElseThrow();

        card.setBlocked(false);
        card.setUnblockReason(reason);

        cardRepository.save(card);
    }
}
