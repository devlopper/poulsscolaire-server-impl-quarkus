DROP TABLE IF EXISTS TA_PAIEMENT_FRAIS_AJUSTE;
DROP TABLE IF EXISTS TA_CAISSE_ENREGISTREUSE;
DROP TABLE IF EXISTS TA_PAIEMENT;
DROP TABLE IF EXISTS TA_FRAIS_AJUSTE;
DROP TABLE IF EXISTS TA_FRAIS;
DROP TABLE IF EXISTS TA_ECHEANCE_MONTANT;
DROP TABLE IF EXISTS TA_MONTANT;
DROP TABLE IF EXISTS TA_INSCRIPTION;
DROP TABLE IF EXISTS TA_ELEVE;
DROP TABLE IF EXISTS TA_IDENTITE;
DROP TABLE IF EXISTS TA_SCOLARITE;
DROP TABLE IF EXISTS TA_ECHEANCE;
DROP TABLE IF EXISTS TA_GROUPE_ECHEANCE;
DROP TABLE IF EXISTS TA_MODE_PAIEMENT;
DROP TABLE IF EXISTS TA_TYPE_AFFECTATION;
DROP TABLE IF EXISTS TA_ANCIENNETE;
DROP TABLE IF EXISTS TA_RUBRIQUE;
DROP TABLE IF EXISTS TA_GENRE;

CREATE TABLE TA_GROUPE_ECHEANCE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_GROUPE_ECHEANCE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_GROUPE_ECHEANCE_UK1 UNIQUE (CODE)
) COMMENT='Table des groupes d''échéances';

CREATE TABLE TA_ECHEANCE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	GROUPE VARCHAR(36) NOT NULL COMMENT 'Identifiant du groupe',
	DATE_ TIMESTAMP NOT NULL COMMENT 'Date',
	ECOLE VARCHAR(255) NOT NULL COMMENT 'Identifiant de l''école',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_ECHEANCE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_ECHEANCE_FK1 FOREIGN KEY (GROUPE) REFERENCES TA_GROUPE_ECHEANCE(IDENTIFIANT),
	CONSTRAINT TA_ECHEANCE_UK1 UNIQUE (CODE),
	CONSTRAINT TA_ECHEANCE_UK2 UNIQUE (GROUPE, ECOLE, DATE_)
) COMMENT='Table des échéances';

CREATE TABLE TA_MODE_PAIEMENT (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_MODE_PAIEMENT_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_MODE_PAIEMENT_UK1 UNIQUE (CODE)
) COMMENT='Table des modes de paiement';

CREATE TABLE TA_CAISSE_ENREGISTREUSE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	ECOLE VARCHAR(255) NOT NULL COMMENT 'Identifiant de l''école',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_CAISSE_ENREGISTREUSE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_CAISSE_ENREGISTREUSE_UK1 UNIQUE (CODE)
) COMMENT='Table des caisses enregistreuses';

CREATE TABLE TA_TYPE_AFFECTATION (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_TYPE_AFFECTATION_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_TYPE_AFFECTATION_UK1 UNIQUE (CODE)
) COMMENT='Table des types d''affectations';

CREATE TABLE TA_ANCIENNETE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_ANCIENNETE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_ANCIENNETE_UK1 UNIQUE (CODE)
) COMMENT='Table des anciennetés';

CREATE TABLE TA_RUBRIQUE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé',
	ECOLE VARCHAR(255) NOT NULL COMMENT 'Identifiant de l''école',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_RUBRIQUE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_RUBRIQUE_UK1 UNIQUE (CODE)
) COMMENT='Table des rubriques';

CREATE TABLE TA_GENRE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code du genre',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé du genre',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_GENRE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_GENRE_UK1 UNIQUE (CODE)
) COMMENT='Table des genres';

CREATE TABLE TA_IDENTITE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	
	MATRICULE VARCHAR(100) COMMENT 'Matricule',
	NOM VARCHAR(100) NOT NULL COMMENT 'Nom',
	NOM_ARABE VARCHAR(100) COMMENT 'Nom arabe',
	PRENOMS VARCHAR(100) NOT NULL COMMENT 'Prénoms',
	PRENOMS_ARABE VARCHAR(100) COMMENT 'Prénoms arabe',
	GENRE VARCHAR(36) NOT NULL COMMENT 'Identifiant du genre',
	GROUPE_SANGUIN VARCHAR(3) COMMENT 'Groupe sanguin',
	DATE_NAISSANCE TIMESTAMP COMMENT 'Date de naissance',
	LIEU_NAISSANCE VARCHAR(255) COMMENT 'Lieu de naissance',
	REFERENCE_EXTRAIT_NAISSANCE VARCHAR(100) COMMENT 'Référence extraite de naissance',
	NATIONALITE VARCHAR(100) COMMENT 'Nationalité',
	SITUATION VARCHAR(100) COMMENT 'Situation',
	PROFESSION VARCHAR(100) COMMENT 'Profession',
	ADRESSE_EMAIL VARCHAR(320) COMMENT 'Adresse email',
	NUMERO_TELEPHONE VARCHAR(20) COMMENT 'Numéro de téléphone',
	LIEU_RESIDENCE VARCHAR(1024) COMMENT 'Lieu de résidence',
	AUTRES_CONTACTS VARCHAR(100) COMMENT 'Autres contacts',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_IDENTITE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_IDENTITE_FK1 FOREIGN KEY (GENRE) REFERENCES TA_GENRE(IDENTIFIANT),
	CONSTRAINT TA_IDENTITE_UK1 UNIQUE (MATRICULE)
) COMMENT='Table des identités';

CREATE TABLE TA_ELEVE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	IDENTITE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''identité',
	ECOLE VARCHAR(255) NOT NULL COMMENT 'Identifiant de l''école',
	ECOLE_ORIGINE VARCHAR(255) COMMENT 'Ecole d''origine',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_ELEVE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_ELEVE_FK1 FOREIGN KEY (IDENTITE) REFERENCES TA_IDENTITE(IDENTIFIANT),
	CONSTRAINT TA_ELEVE_UK1 UNIQUE (CODE)
) COMMENT='Table des élèves';

CREATE TABLE TA_SCOLARITE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	ECOLE VARCHAR(36) NOT NULL COMMENT 'Référence de l''école',
	BRANCHE VARCHAR(36) NOT NULL COMMENT 'Référence de la branche',
	PERIODE VARCHAR(36) NOT NULL COMMENT 'Référence de la période',
	MONTANT_PRE_INSCRIPTION INT NOT NULL COMMENT 'Montant de la pré-inscription',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_SCOLARITE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_SCOLARITE_UK1 UNIQUE (CODE),
	CONSTRAINT TA_SCOLARITE_UK2 UNIQUE (ECOLE, BRANCHE, PERIODE)
) COMMENT='Table des scolarités';

CREATE TABLE TA_MONTANT (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	VALEUR INT NOT NULL COMMENT 'Valeur',
	VALEUR_INSCRIPTION INT COMMENT 'Valeur à l''inscription',
	FACULTATIF BOOLEAN COMMENT 'Facultativité',
	RECONDUCTIBLE BOOLEAN COMMENT 'Reconductibilité',
	NUMERO_ORDRE_PAIEMENT INT COMMENT 'Numéro d''ordre de paiement',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_MONTANT_PK PRIMARY KEY (IDENTIFIANT)
) COMMENT='Table des montants';

CREATE TABLE TA_ECHEANCE_MONTANT (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	
	MONTANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du montant',
	ECHEANCE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''échéance',
	PAIEMENT INT NOT NULL COMMENT 'Paiement',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_ECHEANCE_MONTANT_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_ECHEANCE_MONTANT_FK1 FOREIGN KEY (MONTANT) REFERENCES TA_MONTANT(IDENTIFIANT),
	CONSTRAINT TA_ECHEANCE_MONTANT_FK2 FOREIGN KEY (ECHEANCE) REFERENCES TA_ECHEANCE(IDENTIFIANT),
	CONSTRAINT TA_ECHEANCE_MONTANT_UK1 UNIQUE (MONTANT, ECHEANCE)
) COMMENT='Table des échéances de montants';

CREATE TABLE TA_FRAIS (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	RUBRIQUE VARCHAR(36) NOT NULL COMMENT 'Identifiant de la rubrique',
	TYPE_AFFECTATION VARCHAR(36) NOT NULL COMMENT 'Identifiant du type d''affectation',
	ANCIENNETE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''ancienneté',
	SCOLARITE VARCHAR(36) NOT NULL COMMENT 'Identifiant de la scolarité',
	MONTANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du montant',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_FRAIS_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_FRAIS_FK1 FOREIGN KEY (RUBRIQUE) REFERENCES TA_RUBRIQUE(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_FK2 FOREIGN KEY (TYPE_AFFECTATION) REFERENCES TA_TYPE_AFFECTATION(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_FK3 FOREIGN KEY (ANCIENNETE) REFERENCES TA_ANCIENNETE(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_FK4 FOREIGN KEY (SCOLARITE) REFERENCES TA_SCOLARITE(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_FK5 FOREIGN KEY (MONTANT) REFERENCES TA_MONTANT(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_UK1 UNIQUE (MONTANT),
	CONSTRAINT TA_FRAIS_UK2 UNIQUE (RUBRIQUE, TYPE_AFFECTATION, ANCIENNETE, SCOLARITE)
) COMMENT='Table des frais';

CREATE TABLE TA_INSCRIPTION (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	CODE VARCHAR(16) NOT NULL COMMENT 'Code',

	ELEVE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''élève',
	TYPE_AFFECTATION VARCHAR(36) NOT NULL COMMENT 'Identifiant du type d''affectation',
	ANCIENNETE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''ancienneté',
	SCOLARITE VARCHAR(36) NOT NULL COMMENT 'Identifiant de la scolarité',
	MONTANT_PRE_INSCRIPTION INT NOT NULL COMMENT 'Montant de la pré-inscription',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_INSCRIPTION_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_INSCRIPTION_FK1 FOREIGN KEY (ELEVE) REFERENCES TA_ELEVE(IDENTIFIANT),
	CONSTRAINT TA_INSCRIPTION_FK2 FOREIGN KEY (TYPE_AFFECTATION) REFERENCES TA_TYPE_AFFECTATION(IDENTIFIANT),
	CONSTRAINT TA_INSCRIPTION_FK3 FOREIGN KEY (ANCIENNETE) REFERENCES TA_ANCIENNETE(IDENTIFIANT),
	CONSTRAINT TA_INSCRIPTION_FK4 FOREIGN KEY (SCOLARITE) REFERENCES TA_SCOLARITE(IDENTIFIANT),
	CONSTRAINT TA_INSCRIPTION_UK1 UNIQUE (CODE),
	CONSTRAINT TA_INSCRIPTION_UK2 UNIQUE (ELEVE, SCOLARITE)
) COMMENT='Table des inscriptions';

CREATE TABLE TA_FRAIS_AJUSTE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	FRAIS VARCHAR(36) NOT NULL COMMENT 'Identifiant du frais',
	INSCRIPTION VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''inscription',
	MONTANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du montant',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_FRAIS_AJUSTE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_FRAIS_AJUSTE_FK1 FOREIGN KEY (FRAIS) REFERENCES TA_FRAIS(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_AJUSTE_FK2 FOREIGN KEY (INSCRIPTION) REFERENCES TA_INSCRIPTION(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_AJUSTE_FK3 FOREIGN KEY (MONTANT) REFERENCES TA_MONTANT(IDENTIFIANT),
	CONSTRAINT TA_FRAIS_AJUSTE_UK1 UNIQUE (FRAIS, INSCRIPTION, MONTANT)
) COMMENT='Table des frais ajustés';

CREATE TABLE TA_PAIEMENT (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',
	CODE VARCHAR(16) NOT NULL COMMENT 'Code',
	
	INSCRIPTION VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''inscription',
	MONTANT INT NOT NULL COMMENT 'Montant du paiement',
	DATE_ DATE NOT NULL COMMENT 'Date du paiement',
	MODE VARCHAR(36) NOT NULL COMMENT 'Identifiant du mode de paiement',
	ANNULE BOOLEAN COMMENT 'Annulé',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_PAIEMENT_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_PAIEMENT_FK1 FOREIGN KEY (MODE) REFERENCES TA_MODE_PAIEMENT(IDENTIFIANT)
) COMMENT='Table des paiements';

CREATE TABLE TA_PAIEMENT_FRAIS_AJUSTE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant',

	MONTANT INT NOT NULL COMMENT 'Montant',
	PAIEMENT VARCHAR(36) NOT NULL COMMENT 'Identifiant du paiement',
	FRAIS_AJUSTE VARCHAR(36) NOT NULL COMMENT 'Identifiant du frais ajusté',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_PAIEMENT_FRAIS_AJUSTE_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_PAIEMENT_FRAIS_AJUSTE_FK1 FOREIGN KEY (PAIEMENT) REFERENCES TA_PAIEMENT(IDENTIFIANT),
	CONSTRAINT TA_PAIEMENT_FRAIS_AJUSTE_FK2 FOREIGN KEY (FRAIS_AJUSTE) REFERENCES TA_FRAIS_AJUSTE(IDENTIFIANT),
	CONSTRAINT TA_PAIEMENT_FRAIS_AJUSTE_UK1 UNIQUE (PAIEMENT, FRAIS_AJUSTE)
) COMMENT='Table des paiements des frais ajustés';

INSERT INTO TA_GENRE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('71ec973b-ad3a-46b0-821a-de35a73421a1','F','Féminin','71593f89-63ad-4813-9dd8-c37355d48b4d','christian','Création genre','CREATION','2024-04-08 10:52:39','8a08f486-dc79-4379-8120-ab4113aac14d');
INSERT INTO TA_GENRE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('7d4ada55-39b1-4835-9bb5-4b31519ceccb','M','Masculin','021b6ad0-fc9d-43ba-b018-50dbb9d5ec95','christian','Création genre','CREATION','2024-04-08 10:52:46','33f6562d-e750-4245-98ba-81ace86a907c');
COMMIT;

INSERT INTO TA_ANCIENNETE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('94fe14ce-448b-4dc6-9662-d02121feac49','ANC','Ancien','4d79f574-ab5b-4617-a5ea-6b475e78d460','christian','Création ancienneté','CREATION','2024-04-08 10:53:20','630f2ca8-e557-45cc-8e59-14ce32eaff62');
INSERT INTO TA_ANCIENNETE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('c4033457-4af9-4436-a740-c4e8a5765dad','NOU','Nouveau','b097c0f6-d615-48bd-9875-6e6f0089c221','christian','Création ancienneté','CREATION','2024-04-08 10:53:27','92fe3139-7d8f-4ea4-8b93-65cf84150380');
COMMIT;

INSERT INTO TA_TYPE_AFFECTATION (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('17ee7ff4-a05f-4875-bd72-42aa239781e1','NAFF','Non affecté','39e4071e-d010-492c-b5a9-bedd342f1e35','christian','Création type d\'affectation','CREATION','2024-04-08 10:53:09','7baae3c7-5e2d-4e08-8965-c98722a0e132');
INSERT INTO TA_TYPE_AFFECTATION (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('ac5a7195-cafb-4b23-808d-50d70692a444','AFF','Affecté','ac53ce16-0868-4017-aef3-389638917e8c','christian','Création type d\'affectation','CREATION','2024-04-08 10:52:59','56dc8376-dea2-42ef-a5b2-73ae8bb1eb5a');
COMMIT;

INSERT INTO TA_GROUPE_ECHEANCE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('66ad61ce-abcc-494e-9286-220af63f45e8','MOIS','Mois','a0bf0711-0483-4412-b3ae-6d612ced3aac','christian','Création groupe d\'échéance','CREATION','2024-05-04 18:23:10','7b89da13-31fd-4941-8ce8-662b0d8553c0');
INSERT INTO TA_GROUPE_ECHEANCE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('945ea068-4242-4b57-a2e4-a06bf679826f','ANNUEL','Annuel','2e6c3ea3-b22d-48e6-aafc-c3e7269e6ebf','christian','Création groupe d\'échéance','CREATION','2024-05-04 18:23:38','904e34ef-9c2f-4027-9fad-a37b7ac52a59');
INSERT INTO TA_GROUPE_ECHEANCE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('98254fdf-9c14-44b3-82ed-1b76fb4e7294','TRIMESTRE','Trimestre','69313a70-6d1a-4e3d-b2d9-000228d508b3','christian','Création groupe d\'échéance','CREATION','2024-05-04 18:23:21','4f66e2ca-5857-4183-a932-d3eaa70262f9');
INSERT INTO TA_GROUPE_ECHEANCE (IDENTIFIANT,CODE,LIBELLE,AUDIT_IDENTIFIANT,AUDIT_ACTEUR,AUDIT_FONCTIONNALITE,AUDIT_ACTION,AUDIT_DATE,AUDIT_SESSION) VALUES ('d4a37543-53d0-4051-9b26-388698df8cd0','SEMESTRE','Semestre','f2f1b254-69a3-42d7-ba77-0210372c0c6d','christian','Création groupe d\'échéance','CREATION','2024-05-04 18:23:29','f558b287-56da-4087-befb-3d4e3a1fc754');
COMMIT;

#---------------------------------------------------------------------------------------------------
#--- Audit -----------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#--CREATE SEQUENCE  "POULSSCOLAIRE"."REVINFO_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 1 CACHE 5;