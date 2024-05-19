INSERT INTO TA_MODE_PAIEMENT(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_GENRE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('M','M','Masculin','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','4','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5','5','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','E','B','P','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_GROUPE_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,GROUPE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');

/* Fees */

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','1','1','2','1','2','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','1','1','3','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','1','1','4','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5','1','1','5','1','1','1','1','1','CREATION',DATE '2000-1-1');

/* Registrations and Fees */

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','komenan','christian','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('onepayable_amountless','1','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1_1',1000,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1_1','1','onepayable_amountless','1_1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','2','2','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2_1',1,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2_1','2','2','2_1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','3','3','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('nofees','3','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','4','4','4','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('onepayable_amountequal','4','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4_1',1,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4_1','4','onepayable_amountequal','4_1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5','5','5','5','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('twopayables_amountless','5','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5_1',10,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5_1','1','twopayables_amountless','5_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5_2',20,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('5_2','2','twopayables_amountless','5_2','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6','6','6','6','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('twopayables_oneoptional_amountless','6','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6_1',10,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6_1','1','twopayables_oneoptional_amountless','6_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6_2',20,0,1,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('6_2','2','twopayables_oneoptional_amountless','6_2','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7','7','7','7','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,TYPE_AFFECTATION,ANCIENNETE,ELEVE,SCOLARITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('twopayables_amountlessone','7','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7_1',10,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7_1','1','twopayables_amountlessone','7_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7_2',20,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('7_2','2','twopayables_amountlessone','7_2','1','1','1','CREATION',DATE '2000-1-1');
