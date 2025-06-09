package com.supernova.supernovamvc.domains;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "alerta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String tipoAlerta;
    private Double latitude;
    private Double longitude;
}
