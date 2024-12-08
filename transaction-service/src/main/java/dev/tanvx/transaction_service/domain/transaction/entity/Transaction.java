package dev.tanvx.transaction_service.domain.transaction.entity;

import dev.tanvx.transaction_service.domain.AbstractEntity;
import dev.tanvx.transaction_service.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "note")
    private String note;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
