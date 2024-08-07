-- École
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid) VALUES (1);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid) VALUES (2);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid) VALUES (3);
INSERT INTO ECOLEVIEDBV2.ECOLE(ecoleid) VALUES (4);

-- Programme
INSERT INTO ECOLEVIEDBV2.PROGRAMME(id) VALUES ('1');
INSERT INTO ECOLEVIEDBV2.PROGRAMME(id) VALUES ('2');
INSERT INTO ECOLEVIEDBV2.PROGRAMME(id) VALUES ('3');

-- Branche
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (1,'1');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (2,'1');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (3,'1');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (4,'1');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (5,'1');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (6,'3');
INSERT INTO ECOLEVIEDBV2.BRANCHE(id,fk_programme_id) VALUES (7,'3');

-- Programme école
INSERT INTO ECOLEVIEDBV2.PROGRAMME_ECOLE(id,fk_programme_id,fk_ecole_id) VALUES ('1','1',1);
INSERT INTO ECOLEVIEDBV2.PROGRAMME_ECOLE(id,fk_programme_id,fk_ecole_id) VALUES ('2','2',2);
INSERT INTO ECOLEVIEDBV2.PROGRAMME_ECOLE(id,fk_programme_id,fk_ecole_id) VALUES ('3','3',3);

