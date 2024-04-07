DROP TABLE IF EXISTS TA_PAIEMENT_FRAIS_AJUSTE;
DROP TABLE IF EXISTS TA_PAIEMENT;
DROP TABLE IF EXISTS TA_FRAIS_AJUSTE;
DROP TABLE IF EXISTS TA_FRAIS;
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du groupe d''échéance',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code du groupe d''échéance',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé du groupe d''échéance',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''échéance',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code de l''échéance',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé de l''échéance',
	GROUPE VARCHAR(36) NOT NULL COMMENT 'Identifiant du groupe',
	DATE_ TIMESTAMP NOT NULL COMMENT 'Date de l''échéance',
	
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
	CONSTRAINT TA_ECHEANCE_UK2 UNIQUE (GROUPE, DATE_)
) COMMENT='Table des échéances';

CREATE TABLE TA_MODE_PAIEMENT (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du mode de paiement',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code du mode de paiement',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé du mode de paiement',
	
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

CREATE TABLE TA_TYPE_AFFECTATION (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du type d''affectation',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code du type d''affectation',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé du type d''affectation',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''ancienneté',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code de l''ancienneté',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé de l''ancienneté',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de la rubrique',

	CODE VARCHAR(16) NOT NULL COMMENT 'Code de la rubrique',
	LIBELLE VARCHAR(255) NOT NULL COMMENT 'Libellé de la rubrique',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du genre',

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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''identité',
	
	NOM VARCHAR(100) NOT NULL COMMENT 'Nom de l''identité',
	PRENOMS VARCHAR(100) NOT NULL COMMENT 'Prénoms de l''identité',
	GENRE VARCHAR(36) NOT NULL COMMENT 'Identifiant du genre de l''identité',
	ADRESSE_EMAIL VARCHAR(20) COMMENT 'Adresse email de l''identité',
	NUMERO_TELEPHONE VARCHAR(20) COMMENT 'Numéro de téléphone de l''identité',
	
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
	CONSTRAINT TA_IDENTITE_UK1 UNIQUE (ADRESSE_EMAIL)
) COMMENT='Table des identités';

CREATE TABLE TA_ELEVE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''élève',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code de l''élève',
	MATRICULE VARCHAR(100) COMMENT 'Matricule de l''élève',
	IDENTITE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''identité',
	
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
	CONSTRAINT TA_ELEVE_UK1 UNIQUE (CODE),
	CONSTRAINT TA_ELEVE_UK2 UNIQUE (MATRICULE)
) COMMENT='Table des élèves';

CREATE TABLE TA_SCOLARITE (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de la scolarité',
	
	CODE VARCHAR(16) NOT NULL COMMENT 'Code de la scolarité',
	ECOLE VARCHAR(36) NOT NULL COMMENT 'Référence de l''école',
	BRANCHE VARCHAR(36) NOT NULL COMMENT 'Référence de la branche',
	PERIODE VARCHAR(36) NOT NULL COMMENT 'Référence de la période',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du montant',

	VALEUR BIGINT NOT NULL COMMENT 'Valeur du montant',
	VALEUR_INSCRIPTION BIGINT COMMENT 'Valeur à l''inscriptiondu montant',
	FACULTATIF BOOLEAN COMMENT 'Facultativité du montant',
	RECONDUCTIBLE BOOLEAN COMMENT 'Reconductibilité du montant',
	NUMERO_ORDRE_PAIEMENT INT COMMENT 'Numéro d''ordre de paiement du montant',
	ECHEANCE VARCHAR(36) COMMENT 'Identifiant de l''échéance du montant',
	
	-- Audit
	 AUDIT_IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''audit',
	 AUDIT_ACTEUR VARCHAR(64) NOT NULL COMMENT 'Qui a fait',
	 AUDIT_FONCTIONNALITE VARCHAR(64) NOT NULL COMMENT 'La fonctionnalité faite',
	 AUDIT_ACTION VARCHAR(12) NOT NULL COMMENT 'Quoi a été fait sur la donnée',
	 AUDIT_DATE TIMESTAMP NOT NULL COMMENT 'Quand cela a été fait',
	 AUDIT_SESSION VARCHAR(36) COMMENT 'Dans quelle session cela a été fait',
	 
	-- Constraints
	CONSTRAINT TA_MONTANT_PK PRIMARY KEY (IDENTIFIANT),
	CONSTRAINT TA_MONTANT_FK1 FOREIGN KEY (ECHEANCE) REFERENCES TA_ECHEANCE(IDENTIFIANT)
) COMMENT='Table des montants';

CREATE TABLE TA_FRAIS (
	-- Identification
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de frais',

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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''inscription',
	CODE VARCHAR(16) NOT NULL COMMENT 'Code de l''inscription',

	ELEVE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''élève',
	TYPE_AFFECTATION VARCHAR(36) NOT NULL COMMENT 'Identifiant du type d''affectation',
	ANCIENNETE VARCHAR(36) NOT NULL COMMENT 'Identifiant de l''ancienneté',
	SCOLARITE VARCHAR(36) NOT NULL COMMENT 'Identifiant de la scolarité',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du frais ajuste',

	FRAIS VARCHAR(36) NOT NULL COMMENT 'Identifiant du frais ajuste',
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant de paiement',

	MONTANT INT NOT NULL COMMENT 'Montant du paiement',
	DATE_ DATE NOT NULL COMMENT 'Date du paiement',
	MODE VARCHAR(36) NOT NULL COMMENT 'Identifiant du mode de paiement',
	
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
	IDENTIFIANT VARCHAR(36) NOT NULL COMMENT 'Identifiant du paiement de frais d''inscription',

	MONTANT INT NOT NULL COMMENT 'Montant du paiement de frais d''inscription',
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

#---------------------------------------------------------------------------------------------------
#--- Audit -----------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#--CREATE SEQUENCE  "POULSSCOLAIRE"."REVINFO_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 1 CACHE 5;