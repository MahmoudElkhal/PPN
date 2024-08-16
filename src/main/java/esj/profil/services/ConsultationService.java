package esj.profil.services;

import esj.profil.models.Consultation;
import esj.profil.dtos.ConsultationDTO;

import java.util.List;

public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);

    Consultation updateConsultation(Long id, ConsultationDTO consultationDTO);

    void deleteConsultation(Long id);

    Consultation getConsultationById(Long id);

    List<Consultation> getAllConsultations();

}
