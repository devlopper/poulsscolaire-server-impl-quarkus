INSERT INTO TA_GENRE(IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE) VALUES ('M','M','Masculin','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('1','komenan','christian','M','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_IDENTITE(IDENTIFIANT,NOM,PRENOMS,GENRE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('2','komenan','christian','M','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_RELATION_IDENTITE(IDENTIFIANT,PARENT,ENFANT,TYPE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('1','1','2','PERE','1','1','1','CREATION',DATE '2000-1-1');

INSERT INTO TA_RELATION_IDENTITE(IDENTIFIANT,PARENT,ENFANT,TYPE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE)
VALUES ('2','2','1','MERE','1','1','1','CREATION',DATE '2000-1-1');