package esj.profil.services;

import esj.profil.models.Consultation;
import esj.profil.models.DossierMedical;

import java.util.List;

public interface DossierMedicalService {

    DossierMedical getById(Long id);

    List<DossierMedical> getAllDossiersMedicaux();

    DossierMedical saveOrUpdate(DossierMedical dossierMedical);

    void deleteById(Long id);

    DossierMedical addConsultation(Long dossierMedicalId, Consultation consultation);
}
