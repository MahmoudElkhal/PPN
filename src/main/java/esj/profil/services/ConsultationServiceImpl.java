package esj.profil.services;

import esj.profil.daos.ConsultationRepository;
import esj.profil.models.AntecedentFamilial;
import esj.profil.models.AntecedentPersonnel;
import esj.profil.models.Consultation;
import esj.profil.models.ExamenMedical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import esj.profil.dtos.ConsultationDTO;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found with id " + id));
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation updateConsultation(Long id, ConsultationDTO consultationDTO) {
        try {
            Optional<Consultation> optionalConsultation = consultationRepository.findById(id);

            if (optionalConsultation.isPresent()) {
                Consultation consultation = optionalConsultation.get();

                List<ExamenMedical> examenMedicals = consultationDTO.getExamenMedicals().stream()
                        .map(examenMedicalDTO -> ExamenMedical.builder()
                                .typeExamen(examenMedicalDTO.getTypeExamen())
                                .specificationExamen(examenMedicalDTO.getSpecificationExamen())
                                .autreSpecification(examenMedicalDTO.getAutreSpecification())
                                .build())
                        .collect(Collectors.toList());

                consultation.setDate(consultationDTO.getDate());
                consultation.setMotif(consultationDTO.getMotif());
                consultation.setAntecedentPersonnel(AntecedentPersonnel.builder()
                        .type(consultationDTO.getAntecedentPersonnel().getType())
                        .specification(consultationDTO.getAntecedentPersonnel().getSpecification())
                        .specificationAutre(consultationDTO.getAntecedentPersonnel().getSpecificationAutre())
                        .nombreAnnee(consultationDTO.getAntecedentPersonnel().getNombreAnnee())
                        .build());
                consultation.setAntecedentFamilial(AntecedentFamilial.builder()
                        .typeAntFam(consultationDTO.getAntecedentFamilial().getTypeAntFam())
                        .autre(consultationDTO.getAntecedentFamilial().getAutre())
                        .build());
                consultation.setInterrogatoire(consultationDTO.getInterrogatoire());
                consultation.setExamenMedicals(examenMedicals);
                consultation.setConseils(consultationDTO.getConseils());

                consultationRepository.save(consultation);
                return consultation;
            } else {
                System.out.println("Consultation not found");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
