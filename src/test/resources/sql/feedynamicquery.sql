INSERT INTO TA_GENRE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('M','M','Masculin','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MODE_PAIEMENT(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,ECOLE,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,ECOLE,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','1','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,ECOLE,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','1','3','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,ECOLE,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','1','4','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','E','B','P','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','E','B','P','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','E','B','P','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1',100,0,0,0,0,'1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2',200,50,0,0,0,'1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','1','1','1','2','2','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3',300,25,0,0,0,'1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','1','1','2','2','3','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4',1000,500,1,0,0,'1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('4','1','1','3','2','4','1','1','1','CREATION',DATE '2000-1-1');