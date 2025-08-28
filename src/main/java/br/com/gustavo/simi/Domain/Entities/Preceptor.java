package br.com.gustavo.simi.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preceptors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preceptor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String crm;
}
