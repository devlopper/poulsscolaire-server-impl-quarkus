INSERT INTO ECOLEVIEDBV2.UTILISATEUR(utilisateurid,utilisateu_login,utilisateur_mot_de_passe) VALUES (1,'yao','mp');
INSERT INTO ECOLEVIEDBV2.UTILISATEUR(utilisateurid,utilisateu_login,utilisateur_mot_de_passe) VALUES (2,'ali','mp');
INSERT INTO ECOLEVIEDBV2.UTILISATEUR(utilisateurid,utilisateu_login,utilisateur_mot_de_passe) VALUES (3,'aka','mp');

INSERT INTO ECOLEVIEDBV2.UTILISATEUR_HAS_PERSONNEL(utilisateur_has_personnelid,utilisateur_utilisateurid,profil_profilid) 
VALUES (1,1,1);
INSERT INTO ECOLEVIEDBV2.UTILISATEUR_HAS_PERSONNEL(utilisateur_has_personnelid,utilisateur_utilisateurid,profil_profilid) 
VALUES (2,1,2);
INSERT INTO ECOLEVIEDBV2.UTILISATEUR_HAS_PERSONNEL(utilisateur_has_personnelid,utilisateur_utilisateurid,profil_profilid) 
VALUES (3,1,3);
INSERT INTO ECOLEVIEDBV2.UTILISATEUR_HAS_PERSONNEL(utilisateur_has_personnelid,utilisateur_utilisateurid,profil_profilid) 
VALUES (4,2,1);