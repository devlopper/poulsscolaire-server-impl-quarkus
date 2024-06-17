INSERT INTO TA_GENRE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('M','M','Masculin','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_GROUPE_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,GROUPE,DATE_,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1',DATE '2000-1-1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','4','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','E','B','P','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1','feesvalue1','feesvalue1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_1',100000,25000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_1','1','1','1','feesvalue1','feesvalue1_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_2',30000,0,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_2','1','1','2','feesvalue1','feesvalue1_2','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_3',10000,0,1,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_3','1','1','3','feesvalue1','feesvalue1_3','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','komenan','christian','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');