# docs/api-notes.md — Atelier 5 : API REST AutoLoc

## A. Endpoints

| Controleur | Verbe | Route | Service appele | Succes | Erreurs |
|---|---|---|---|---|---|
| AgenceController | GET | /api/agences | findAll() | 200 | — |
| AgenceController | GET | /api/agences/{id} | findById() | 200 | 404 |
| AgenceController | POST | /api/agences | create() | 201 | 400 |
| AgenceController | PUT | /api/agences/{id} | update() | 200 | 404, 400 |
| AgenceController | DELETE | /api/agences/{id} | deleteById() | 204 | 404 |
| ClientController | GET | /api/clients | findAll() | 200 | — |
| ClientController | GET | /api/clients/{id} | findById() | 200 | 404 |
| ClientController | POST | /api/clients | create() | 201 | 400 |
| ClientController | PUT | /api/clients/{id} | update() | 200 | 404, 400 |
| ClientController | DELETE | /api/clients/{id} | deleteById() | 204 | 404 |
| VehiculeController | GET | /api/vehicules | findAll() | 200 | — |
| VehiculeController | GET | /api/vehicules/{id} | findById() | 200 | 404 |
| VehiculeController | POST | /api/vehicules | create() | 201 | 400 |
| VehiculeController | PUT | /api/vehicules/{id} | update() | 200 | 404, 400 |
| VehiculeController | DELETE | /api/vehicules/{id} | deleteById() | 204 | 404 |
| EmployeController | GET | /api/employes | findAll() | 200 | — |
| EmployeController | GET | /api/employes/{id} | findById() | 200 | 404 |
| EmployeController | POST | /api/employes | create() | 201 | 400 |
| EmployeController | PUT | /api/employes/{id} | update() | 200 | 404, 400 |
| EmployeController | DELETE | /api/employes/{id} | deleteById() | 204 | 404 |
| ReservationController | GET | /api/reservations | findAll() | 200 | — |
| ReservationController | GET | /api/reservations/{id} | findById() | 200 | 404 |
| ReservationController | POST | /api/reservations | create() | 201 | 400 |
| ReservationController | PUT | /api/reservations/{id} | update() | 200 | 404, 400 |
| ReservationController | DELETE | /api/reservations/{id} | deleteById() | 204 | 404 |
| ContratController | GET | /api/contrats | findAll() | 200 | — |
| ContratController | GET | /api/contrats/{id} | findById() | 200 | 404 |
| ContratController | POST | /api/contrats | create() | 201 | 400 |
| ContratController | PUT | /api/contrats/{id} | update() | 200 | 404, 400 |
| ContratController | DELETE | /api/contrats/{id} | deleteById() | 204 | 404 |
| PaiementController | GET | /api/paiements | findAll() | 200 | — |
| PaiementController | GET | /api/paiements/{id} | findById() | 200 | 404 |
| MaintenanceController | GET | /api/maintenances | findAll() | 200 | — |
| MaintenanceController | GET | /api/maintenances/{id} | findById() | 200 | 404 |
| MaintenanceController | POST | /api/maintenances | create() | 201 | 400 |
| MaintenanceController | PUT | /api/maintenances/{id} | update() | 200 | 404, 400 |
| MaintenanceController | DELETE | /api/maintenances/{id} | deleteById() | 204 | 404 |
| EquipementController | GET | /api/equipements | findAll() | 200 | — |
| EquipementController | GET | /api/equipements/{id} | findById() | 200 | 404 |
| EquipementController | POST | /api/equipements | create() | 201 | 400 |
| EquipementController | PUT | /api/equipements/{id} | update() | 200 | 404, 400 |
| EquipementController | DELETE | /api/equipements/{id} | deleteById() | 204 | 404 |

---

## B. Annotations Jackson

| Association | Annotation retenue | Justification |
|---|---|---|
| Contrat → paiements | `@JsonManagedReference("contrat-paiements")` | Cote parent : serialise normalement la liste des paiements |
| Paiement → contrat | `@JsonBackReference("contrat-paiements")` | Cote enfant : ignore le champ pour couper la boucle |
| Agence → vehicules | `@JsonIgnore` | Evite la boucle Agence→Vehicule→Agence |
| Agence → employes | `@JsonIgnore` | Evite la boucle Agence→Employe→Agence |
| Client → reservations | `@JsonIgnore` | Evite la boucle Client→Reservation→Client |
| Vehicule → reservations, maintenances, equipements, agence | `@JsonIgnore` | Evite toutes les boucles issues de Vehicule |
| Reservation → contrat | `@JsonIgnore` | Evite la boucle Reservation→Contrat→Reservation |
| Equipement → vehicules | `@JsonIgnore` | Evite la boucle Equipement→Vehicule→Equipement |

**Difference entre @JsonBackReference et @JsonIgnore :**
- `@JsonBackReference` : fait partie d'une paire avec `@JsonManagedReference`. Le champ est ignore a la serialisation mais reste lisible a la deserialisation (lecture JSON entrant). Utilise quand il y a une vraie composition parent/enfant (cascade ALL).
- `@JsonIgnore` : exclut completement le champ de la serialisation ET de la deserialisation. Utilise quand on veut juste couper la boucle sans relation de composition.

---

## C. Postman et Swagger

**Postman** sert a tester : on ecrit des requetes rejouables avec des assertions automatiques (pm.test). Exemple concret : apres POST /api/contrats, le script sauvegarde automatiquement l'id dans contratId pour le reutiliser dans PUT et DELETE.

**Swagger UI** sert a documenter et explorer : il genere une page web interactive depuis les annotations du code. Exemple concret : en ouvrant http://localhost:8090/api/swagger-ui.html, on voit tous les endpoints groupes par tag (Contrats, Paiements, etc.) avec les codes de reponse documentes.

Les deux sont complementaires : Postman pour les tests automatises, Swagger pour la documentation vivante.

---

## D. Anomalies SonarQube

| Anomalie | Regle | Explication | Correction |
|---|---|---|---|
| Injection par attribut (@Autowired sur un champ) | java:S6813 | L'injection par attribut rend la classe difficile a tester et cache les dependances. | Remplacer par injection par constructeur. Avec `@RequiredArgsConstructor` + champ `final`, Lombok genere le constructeur automatiquement. |
| Entite JPA exposee directement dans le controleur | java:S6838 | Exposer l'entite JPA revele la structure interne de la base de donnees et cree un couplage fort. | Utiliser des DTO (objet de transfert) pour separer le modele de la representation API. (Prevu au chapitre 10) |

---

## E. Questions de comprehension

**1. Quel composant recoit toutes les requetes, et comment retrouve-t-il la methode du controleur ?**
Le `DispatcherServlet` recoit toutes les requetes HTTP. Il consulte le registre des mappings (construit au demarrage a partir des annotations `@RequestMapping`, `@GetMapping`, etc.) pour trouver la methode du controleur correspondant au verbe HTTP et a l'URL.

**2. Pourquoi ContratController depend-il de IContratService et non de ContratRepository ou ContratServiceImpl ?**
Pour respecter le principe d'inversion de dependance : le controleur depend d'une abstraction (l'interface), pas d'une implementation concrete. Cela permet de changer l'implementation sans modifier le controleur, et facilite les tests unitaires avec un mock.

**3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?**
- POST cree une ressource → 201 Created signifie "ressource creee avec succes".
- DELETE supprime sans renvoyer de corps → 204 No Content signifie "action reussie, pas de contenu a retourner".
- 404 est une erreur client (la ressource demandee n'existe pas), pas une erreur serveur. Le `GlobalExceptionHandler` intercepte `ResourceNotFoundException` et renvoie 404 au lieu de laisser Spring renvoyer 500.

**4. Pourquoi PaiementController n'expose-t-il que des GET ? Quelle reponse recoit un POST /api/paiements ?**
Les paiements sont crees par cascade via le Contrat (cascade = ALL). On ne les cree jamais directement. Un POST /api/paiements recoit **405 Method Not Allowed** car la methode n'est pas mappee.

**5. Quels risques presente l'exposition directe des entites JPA ? Pourquoi Contrat.paiements utilise @JsonManagedReference alors que Agence.vehicules utilise @JsonIgnore ?**
Risques : fuite de donnees internes, couplage API/base de donnees, boucles de serialisation infinies (StackOverflowError).

`Contrat.paiements` utilise `@JsonManagedReference` car c'est une vraie composition (cascade = ALL, orphanRemoval = true) : les paiements font partie du contrat et doivent apparaitre dans le JSON du contrat. `Agence.vehicules` utilise `@JsonIgnore` car les vehicules sont des entites independantes qui ne font pas partie de la representation JSON d'une agence — on les consulte via `/api/vehicules`.
