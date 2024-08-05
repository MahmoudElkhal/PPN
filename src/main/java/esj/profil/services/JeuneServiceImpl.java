package esj.profil.services;

import java.lang.Long;

import ch.qos.logback.core.net.SyslogOutputStream;
import esj.profil.daos.ConsultationRepository;
import esj.profil.daos.JeuneRepository;
import esj.profil.daos.MedecinRepository;
import esj.profil.dtos.ConsultationDTO;
import esj.profil.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JeuneServiceImpl implements JeuneService {
    @Autowired
    private JeuneRepository jeuneRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private MedecinRepository medecinRepository;

    public Jeune getById(Long id) {
        return jeuneRepository.findById(id).orElse(null);
    }

    public List<Jeune> getAllJeunes() {
        return jeuneRepository.findAll();
    }

    public Jeune saveOrUpdate(Jeune jeune) {
        if (jeune.getDossierMedial() == null) {
            DossierMedical dossierMedical = new DossierMedical();
            dossierMedical.setJeune(jeune);
            jeune.setDossierMedial(dossierMedical);
        }
        return jeuneRepository.save(jeune);
    }

    public void deleteById(Long id) {
        jeuneRepository.deleteById(id);
    }

    public Jeune addConsultationDTOToJeune(Long id, ConsultationDTO consultationDTO) {
        try {
            System.out.println(consultationDTO);
            Optional<Jeune> optionalJeune = jeuneRepository.findById(id);
            if (optionalJeune.isPresent()) {
                Jeune jeune = optionalJeune.get();
                System.out.println("jeune is not null");

                Medecin medecin = medecinRepository.findById(consultationDTO.getMedecinId()).orElse(null);
                if (medecin == null) {
                    System.out.println("Medecin not found");
                    return null;
                }

                DossierMedical dossierMedical = jeune.getDossierMedial(); // Assuming this is correctly fetched
                if (dossierMedical == null) {
                    System.out.println("DossierMedical not found");
                    return null;
                }

                List<ExamenMedical> examenMedicals = consultationDTO.getExamenMedicals().stream()
                        .map(examenMedicalDTO -> ExamenMedical.builder()
                                .typeExamen(examenMedicalDTO.getTypeExamen())
                                .specificationExamen(examenMedicalDTO.getSpecificationExamen())
                                .autreSpecification(examenMedicalDTO.getAutreSpecification())
                                .build())
                        .collect(Collectors.toList());

                Consultation consultation = Consultation.builder()
                        .date(consultationDTO.getDate())
                        .motif(consultationDTO.getMotif())
                        .antecedentPersonnel(AntecedentPersonnel.builder()
                                .type(consultationDTO.getAntecedentPersonnel().getType())
                                .specification(consultationDTO.getAntecedentPersonnel().getSpecification())
                                .specificationAutre(consultationDTO.getAntecedentPersonnel().getSpecificationAutre())
                                .nombreAnnee(consultationDTO.getAntecedentPersonnel().getNombreAnnee())
                                .build())
                        .antecedentFamilial(AntecedentFamilial.builder()
                                .typeAntFam(consultationDTO.getAntecedentFamilial().getTypeAntFam())
                                .autre(consultationDTO.getAntecedentFamilial().getAutre())
                                .build())
                        .interrogatoire(consultationDTO.getInterrogatoire())
                        .examenMedicals(examenMedicals)
                        .conseils(consultationDTO.getConseils())
                        .jeune(jeune)
                        .medecin(medecin)
                        .dossierMedical(dossierMedical)
                        .build();

                return addConsultationToJeune(id, consultation);
            } else {
                System.out.println("Jeune not found");
                return null; //
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // public Jeune addConsultationDTOToJeune(Long id, ConsultationDTO
    // consultationDTO) {
    // System.out.println(consultationDTO);
    // Optional<Jeune> optionalJeune = jeuneRepository.findById(id);
    // if (optionalJeune.isPresent()) {
    // Jeune jeune = optionalJeune.get();
    // System.out.println("jeune is not null");
    // Consultation consultation = Consultation.builder()
    // .date(consultationDTO.getDate())
    // .motif(consultationDTO.getMotif())
    // .antecedentPersonnel(AntecedentPersonnel.builder()
    // .type(consultationDTO.getAntecedentPersonnel().getType())
    // .specification(consultationDTO.getAntecedentPersonnel().getSpecification())
    // .specificationAutre(consultationDTO.getAntecedentPersonnel().getSpecificationAutre())
    // .nombreAnnee(consultationDTO.getAntecedentPersonnel().getNombreAnnee())
    // .build())
    // .antecedentFamilial(AntecedentFamilial.builder()
    // .typeAntFam(consultationDTO.getAntecedentFamilial().getTypeAntFam())
    // .autre(consultationDTO.getAntecedentFamilial().getAutre())
    // .build())
    // .historiqueClinique(consultationDTO.getHistoriqueClinique())
    // .examenClinique(consultationDTO.getExamenClinique())
    // .examenMedical(ExamenMedical.builder()
    // .typeExamen(consultationDTO.getExamenMedical().getTypeExamen())
    // .specificationExamen(consultationDTO.getExamenMedical().getSpecificationExamen())
    // .autreSpecification(consultationDTO.getExamenMedical().getAutreSpecification())
    // .build())
    // .Diagnostic(consultationDTO.getDiagnostic())
    // .Ordonnance(consultationDTO.getOrdonnance())
    // .jeune(jeune)
    // .medecin(medecinRepository.findById(consultationDTO.getMedecinId()).orElse(null))
    // // Assuming you have a constructor or way to set ID
    // .dossierMedical(jeune.getDossierMedial()) // Same assumption
    // .build();
    //
    //
    // return addConsultationToJeune(id,consultation);
    // }
    // return null;
    // }

    @Override
    public Jeune addConsultationToJeune(Long jeuneId, Consultation consultation) {
        Jeune jeune = jeuneRepository.findById(jeuneId).orElse(null);
        if (jeune != null) {
            DossierMedical dossierMedical = jeune.getDossierMedial();
            if (dossierMedical != null) {
                consultation.setDossierMedical(dossierMedical);
                dossierMedical.getHistoriqueConsultations().add(consultation);
                jeuneRepository.save(jeune);
            }
        }
        return jeune;
    }
}
