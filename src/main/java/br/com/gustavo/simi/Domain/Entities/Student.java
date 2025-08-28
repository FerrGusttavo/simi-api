package br.com.gustavo.simi.Domain.Entities;

import br.com.gustavo.simi.Domain.Enums.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String registration;

    @Enumerated(EnumType.STRING)
    private Period period;
}
