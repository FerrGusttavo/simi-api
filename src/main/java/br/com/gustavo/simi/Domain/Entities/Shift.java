package br.com.gustavo.simi.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "shifts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shift {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Specialty specialty;

    @ManyToOne
    private Preceptor preceptor;

    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Integer maxSlots;
}
