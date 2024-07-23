package esj.profil.models;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String motif;
    @Embedded
    private AntecedentPersonnel antecedentPersonnel;
    @Embedded
    private AntecedentFamilial antecedentFamilial;
    private String historiqueClinique;
    private String examenClinique;
    @Embedded
    private ExamenMedical examenMedical;
    private String Diagnostic;
    private String Ordonnance;
    @ManyToOne(cascade = CascadeType.ALL)
    private Jeune jeune;
    @ManyToOne(cascade = CascadeType.ALL)
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "dossier_medical_id")
    private DossierMedical dossierMedical;
}
