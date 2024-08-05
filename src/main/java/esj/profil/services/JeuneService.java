package esj.profil.services;

import esj.profil.dtos.ConsultationDTO;
import esj.profil.models.AntecedentFamilial;
import esj.profil.models.AntecedentPersonnel;
import esj.profil.models.Consultation;
import esj.profil.models.Jeune;

import java.util.List;

public interface JeuneService {
    Jeune getById(Long id);
    List<Jeune> getAllJeunes();
    Jeune saveOrUpdate(Jeune jeune);
    void deleteById(Long id);
    Jeune addConsultationToJeune(Long jeuneId, Consultation consultation);

    Jeune addConsultationDTOToJeune(Long id, ConsultationDTO consultationDTO);
    Jeune addAntecedentPersonnelToJeune(Long id, AntecedentPersonnel antecedentPersonnel);
    Jeune addAntecedentFamilialToJeune(Long id, AntecedentFamilial antecedentFamilial);

}
