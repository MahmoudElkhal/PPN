1.penser a changer les identifiants de la base de donnee (username & mot de passe) dans le fichier application.properties avant toute manipulation.

2.la creation des objets medecin et jeune se font de maniere directe depuis le controller (l'objet json passe au controlleur suit exactement le schema de l'entite qui figure dans le paquage models).

3.la creation de l'objet consultation exige un jeuneId et un medecinId valides (appartenant a des entites jeune et medecin preexistentes)
proceder donc a la creation de ces deux-ci avant de tenter la creation d'une consultation. De plus, la creation d'une consultation passe par un DTO dont le schema peut etre retrouve dans le paquage dtos.
Par exemple : un requete http de type post addressee a http://localhost:8080/jeunes/4/consultations
dont le body est le suivant permet de creer une consultation
{
  "date": "2024-07-23",
  "motif": "General Checkup",
  "antecedentPersonnel": {
    "type": "Diabetes",
    "specification": "Type 2",
    "specificationAutre": "Insulin-dependent",
    "nombreAnnee": 5
  },
  "antecedentFamilial": {
    "typeAntFam": "Heart Disease",
    "autre": "Father had heart attack"
  },
  "historiqueClinique": "Patient has a history of high blood pressure.",
  "examenClinique": "Blood pressure: 140/90, Weight: 80kg",
  "examenMedical": {
    "typeExamen": "Biologique",
    "specificationExamen": "Blood test",
    "autreSpecification": "Fasting glucose level"
  },
  "Diagnostic": "Hypertension",
  "Ordonnance": "Prescribed antihypertensive medication.",
  "jeuneId" : 3,
  "medecinId": 1
  
}







