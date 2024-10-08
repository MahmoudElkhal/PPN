package esj.profil.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExamenMedicalDTO {
    public String typeExamen; // Biologique - Radiologique
    public String specificationExamen;
    public String autreSpecification;
}
