package br.com.taskboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime createdAt;

    private boolean blocked;

    private String blockReason;
    private String unblockReason;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private BoardColumn column;
}
