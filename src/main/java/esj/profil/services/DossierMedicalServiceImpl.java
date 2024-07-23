package esj.profil.services;

import esj.profil.daos.ConsultationRepository;
import esj.profil.models.Consultation;
import esj.profil.models.DossierMedical;
import esj.profil.daos.DossierMedicalRepository;
import esj.profil.services.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierMedicalServiceImpl implements DossierMedicalService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public DossierMedical getById(Long id) {
        Optional<DossierMedical> optionalDossierMedical = dossierMedicalRepository.findById(id);
        return optionalDossierMedical.orElse(null);
    }

    @Override
    public List<DossierMedical> getAllDossiersMedicaux() {
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical saveOrUpdate(DossierMedical dossierMedical) {
        return dossierMedicalRepository.save(dossierMedical);
    }

    @Override
    public void deleteById(Long id) {
        dossierMedicalRepository.deleteById(id);
    }

    @Override
    public DossierMedical addConsultation(Long dossierMedicalId, Consultation consultation) {
        DossierMedical dossierMedical = dossierMedicalRepository.findById(dossierMedicalId).orElse(null);
        if (dossierMedical != null) {
            consultation.setDossierMedical(dossierMedical);
            consultationRepository.save(consultation);
            dossierMedical.getHistoriqueConsultations().add(consultation);
            dossierMedicalRepository.save(dossierMedical);
        }
        return dossierMedical;
    }
}
