1.penser a changer les identifiant de la base de donnee (username & mot de passe) dans le fichier application.properties avant toute
manipulation

2.la creation des objets medecin et jeune se font de maniere directe depuis le controller (l'objet json passe au controlleur suit exactement le schema de l'entite qui figure dans le paquage models)

3.la creation de l'objet consultation exige un jeuneId et un medecinId valides (appartenant a des entites jeune et medecin preexistents)
proceder donc a la creation de ces deux-ci avant de tenter la creation d'une consultation. De plus, la creation d'une consultation passe par un DTO dont le schema peut etre retrouve dans le paquage dtos.







