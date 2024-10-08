package esj.profil.dtos;

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
public class AntecedentPersonnelDTO {
    public String type;
    public String specification;
    public String specificationAutre;
    public int nombreAnnee;
}
