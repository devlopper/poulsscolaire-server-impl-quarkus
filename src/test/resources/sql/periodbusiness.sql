INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid,Niveau_Enseignement_id) VALUES (1,1);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid,Niveau_Enseignement_id) VALUES (2,1);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid,Niveau_Enseignement_id) VALUES (3,1);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid,Niveau_Enseignement_id) VALUES (4,1);

-- Central
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,Niveau_Enseignement_id,niveau) VALUES (1,2020,1,'CENTRAL');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,Niveau_Enseignement_id,niveau) VALUES (2,2021,1,'CENTRAL');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,Niveau_Enseignement_id,niveau) VALUES (3,2022,1,'CENTRAL');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,Niveau_Enseignement_id,niveau) VALUES (4,2023,1,'CENTRAL');

-- Ecole 1
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (5,2020,1,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (6,2021,1,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (7,2022,1,'OUVERT');

-- Ecole 2
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (8,2020,2,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (9,2021,2,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (10,2022,2,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (11,2023,2,'OUVERT');

-- Ecole 3
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (12,2020,3,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (13,2021,3,'FERME');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (14,2022,3,'OUVERT');
INSERT INTO ECOLEVIEDBV2.ANNEE_SCOLAIRE(annee_scolaireid,annee,ecole_id,statut) VALUES (15,2023,3,'OUVERT');