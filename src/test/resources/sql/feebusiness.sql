INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','E','B','P','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_GROUPE_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,GROUPE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('toupdate',0,0,0,0,0,'1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('toupdate','1','1','1','1','toupdate','1','1','1','CREATION',DATE '2000-1-1');