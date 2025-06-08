package com.supernova.supernovamvc.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alertas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    @Id
    private String id;

    @DBRef
    private Usuario usuario;

    private String tipoAlerta;
    private Double latitude;
    private Double longitude;
}
