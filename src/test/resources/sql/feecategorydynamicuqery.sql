INSERT INTO TA_MODE_PAIEMENT(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_GENRE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('M','M','Masculin','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_RUBRIQUE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('3','3','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_TYPE_AFFECTATION(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ANCIENNETE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_GROUPE_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ECHEANCE(IDENTIFIANT,CODE,LIBELLE,GROUPE,DATE_,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1',DATE '2000-1-1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('2','2','unknown','unknown','unknown','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1','feesvalue1','feesvalue1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_1',100000,25000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_1','1','1','1','feesvalue1','feesvalue1_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_2',30000,0,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_2','1','1','2','feesvalue1','feesvalue1_2','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_3',10000,0,1,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue1_3','1','1','3','feesvalue1','feesvalue1_3','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue2','feesvalue2','feesvalue2','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue2_1',215000,72000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue2_1','1','1','1','feesvalue2','feesvalue2_1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue2_2',28000,8000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS(IDENTIFIANT,TYPE_AFFECTATION,ANCIENNETE,RUBRIQUE,SCOLARITE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('feesvalue2_2','1','1','2','feesvalue2','feesvalue2_2','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_SCOLARITE(IDENTIFIANT,CODE,ECOLE,BRANCHE,PERIODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('nofeesvalue','nofeesvalue','nofeesvalue','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO VMA_ECOLE(IDENTIFIANT,LIBELLE) VALUES ('1','CSP Cocody');
INSERT INTO VMA_BRANCHE(IDENTIFIANT,LIBELLE) VALUES ('1','6ieme');
INSERT INTO VMA_PERIODE(IDENTIFIANT,LIBELLE,OUVERTE) VALUES ('1','2023-2024',1);

INSERT INTO VMA_ECOLE(IDENTIFIANT,LIBELLE) VALUES ('feesvalue','1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','M','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_ELEVE(IDENTIFIANT,CODE,MATRICULE,IDENTITE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('1','1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_INSCRIPTION(IDENTIFIANT,CODE,ELEVE,SCOLARITE,TYPE_AFFECTATION,ANCIENNETE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('i1','1','1','feesvalue1','1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af1m',90000,20000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af1','feesvalue1_1','i1','af1m','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af2m',30000,12000,0,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af2','feesvalue1_2','i1','af2m','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_MONTANT(IDENTIFIANT,VALEUR,VALEUR_INSCRIPTION,FACULTATIF,RECONDUCTIBLE,NUMERO_ORDRE_PAIEMENT,ECHEANCE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af3m',10000,0,1,1,1,'1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_FRAIS_AJUSTE(IDENTIFIANT,FRAIS,INSCRIPTION,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('af3','feesvalue1_3','i1','af3m','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_PAIEMENT(IDENTIFIANT,CODE,INSCRIPTION,MODE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('p1','1','i1','1','1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_PAIEMENT_FRAIS_AJUSTE(IDENTIFIANT,PAIEMENT,FRAIS_AJUSTE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('paf1','p1','af1',5,'1','1','1','CREATION',DATE '2000-1-1');
INSERT INTO TA_PAIEMENT_FRAIS_AJUSTE(IDENTIFIANT,PAIEMENT,FRAIS_AJUSTE,MONTANT,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('paf2','p1','af2',30000,'1','1','1','CREATION',DATE '2000-1-1');