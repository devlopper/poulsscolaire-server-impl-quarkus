INSERT INTO TA_PLAN_COMPTABLE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('1','1','1','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_COMPTE_COMPTABLE(IDENTIFIANT,CODE,LIBELLE,PLAN_COMPTABLE,TYPE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('1','1','1','1','D','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_COMPTE_COMPTABLE_ECOLE(IDENTIFIANT,COMPTE,ECOLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('toupdate','1','3','1','1','1','CREATION',DATE '2000-1-1');