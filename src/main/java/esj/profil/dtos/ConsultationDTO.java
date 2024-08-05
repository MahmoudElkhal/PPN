package esj.profil.dtos;

import java.sql.Date;
import java.util.List;

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
public class ConsultationDTO {
    public Date date;
    public String motif;
    public AntecedentPersonnelDTO antecedentPersonnel;
    public AntecedentFamilialDTO antecedentFamilial;
    public String interrogatoire;
    public List<ExamenMedicalDTO> examenMedicals;

    public String conseils;
    public Long jeuneId;
    public Long medecinId;
    public Long dossierMedicalId;
}
