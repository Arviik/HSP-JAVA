CREATE USER 'hsp_java'@'%' IDENTIFIED WITH mysql_native_password BY 'uT$0G&3rAUVp0o5FH9x0zw%B7HXtGo5iJJyICiIHzFeQP!CC4T';
GRANT USAGE ON *.* TO 'hsp_java'@'%';
ALTER USER 'hsp_java'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT SELECT, INSERT, UPDATE, DELETE ON hsp_java.* TO 'hsp_java'@'%';

CREATE DATABASE IF NOT EXISTS hsp_java;
USE hsp_java;

CREATE TABLE utilisateur(
    id_utilisateur int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(255) NOT NULL,
    prenom varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    mot_de_passe varchar(255) NOT NULL,
    PRIMARY KEY (id_utilisateur)
);

CREATE TABLE admin(
    ref_utilisateur int(11) NOT NULL UNIQUE,
    PRIMARY KEY (ref_utilisateur)
);

CREATE TABLE medecin(
    ref_utilisateur int(11) NOT NULL UNIQUE,
    PRIMARY KEY (ref_utilisateur)
);

CREATE TABLE gestionnaire_de_stock(
    ref_utilisateur int(11) NOT NULL UNIQUE,
    PRIMARY KEY (ref_utilisateur)
);

CREATE TABLE secretaire(
    ref_utilisateur int(11) NOT NULL UNIQUE,
    PRIMARY KEY (ref_utilisateur)
);

CREATE TABLE dossier(
    id_dossier int(11) NOT NULL AUTO_INCREMENT,
    date_heure datetime NOT NULL,
    description_symptome text NOT NULL,
	ordonnance text DEFAULT NULL,
    niv_gravite int(1) NOT NULL,
    ref_medecin int(11) DEFAULT NULL,
    ref_patient int(11) NOT NULL,
    ref_secretaire int(11) NOT NULL,
    PRIMARY KEY (id_dossier)
);

CREATE TABLE hospitalisation(
    id_hospitalisation int(11) NOT NULL AUTO_INCREMENT,
    date_heure datetime NOT NULL,
    description_maladie text NOT NULL,
    est_termine BOOLEAN NOT NULL DEFAULT FALSE,
    ref_dossier int(11) NOT NULL,
    ref_chambre int(11) NOT NULL,
    PRIMARY KEY (id_hospitalisation)
);

CREATE TABLE chambre(
    id_chambre int(11) NOT NULL AUTO_INCREMENT,
    numero int(11) UNIQUE NOT NULL,
    PRIMARY KEY (id_chambre)
);

CREATE TABLE patient(
    id_patient int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(255) NOT NULL,
    prenom varchar(255) NOT NULL,
    num_securite_sociale int(15) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    telephone int(10) NOT NULL,
    adresse varchar(255) NOT NULL,
    ref_secretaire int(11) NOT NULL,
    PRIMARY KEY (id_patient)
);

CREATE TABLE fournisseur(
    id_fournisseur int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(255) NOT NULL,
    PRIMARY KEY (id_fournisseur)
);

CREATE TABLE propose(
    prix int(11) NOT NULL,
    ref_fournisseur int(11) NOT NULL,
    ref_produit int(11) NOT NULL,
    PRIMARY KEY (ref_fournisseur,ref_produit)
);

CREATE TABLE produit(
    id_produit int(11) NOT NULL AUTO_INCREMENT,
    libelle varchar(255) NOT NULL,
    description text NOT NULL,
    niv_dangerosite int(1) NOT NULL,
    stock int(11) NOT NULL DEFAULT 0,
    ref_gestionnaire_stock int(11) NOT NULL,
    PRIMARY KEY (id_produit)
);

CREATE TABLE concerne(
    quantite int(11) NOT NULL,
    ref_demande int(11) NOT NULL,
    ref_produit int(11) NOT NULL,
    PRIMARY KEY (ref_produit,ref_demande)
);

CREATE TABLE demande(
    id_demande int(11) NOT NULL AUTO_INCREMENT,
    raison text NOT NULL,
    est_valide BOOLEAN DEFAULT NULL,
    ref_gestionnaire_de_stock int(11) DEFAULT NULL,
    ref_medecin int(11) NOT NULL,
    PRIMARY KEY (id_demande)
);

ALTER TABLE dossier
    ADD CONSTRAINT fk_dossier_medecin FOREIGN KEY (ref_medecin) REFERENCES medecin(ref_utilisateur),
    ADD CONSTRAINT fk_dossier_patient FOREIGN KEY (ref_patient) REFERENCES patient(id_patient),
    ADD CONSTRAINT fk_dossier_secretaire FOREIGN KEY (ref_secretaire) REFERENCES secretaire(ref_utilisateur);

ALTER TABLE hospitalisation
    ADD CONSTRAINT fk_hospitalisation_dossier FOREIGN KEY (ref_dossier) REFERENCES dossier(id_dossier),
    ADD CONSTRAINT fk_hospitalisation_chambre FOREIGN KEY (ref_chambre) REFERENCES chambre(id_chambre);

ALTER TABLE patient
    ADD CONSTRAINT fk_patient_secretaire FOREIGN KEY (ref_secretaire) REFERENCES secretaire(ref_utilisateur);

ALTER TABLE propose
    ADD CONSTRAINT fk_propose_fourniseur FOREIGN KEY (ref_fournisseur) REFERENCES fournisseur(id_fournisseur),
    ADD CONSTRAINT fk_propose_produit FOREIGN KEY (ref_produit) REFERENCES produit(id_produit);

ALTER TABLE produit
    ADD CONSTRAINT fk_produit_gestionnaire_de_stock FOREIGN KEY (ref_gestionnaire_stock) REFERENCES gestionnaire_de_stock(ref_utilisateur);

ALTER TABLE concerne
    ADD CONSTRAINT fk_concerne_demande FOREIGN KEY (ref_demande) REFERENCES demande(id_demande),
    ADD CONSTRAINT fk_concerne_produit FOREIGN KEY (ref_produit) REFERENCES produit(id_produit);

ALTER TABLE demande
    ADD CONSTRAINT fk_demande_gestionnaire_de_stock FOREIGN KEY (ref_gestionnaire_de_stock) REFERENCES gestionnaire_de_stock(ref_utilisateur),
    ADD CONSTRAINT fk_demande_medecin FOREIGN KEY (ref_medecin) REFERENCES medecin(ref_utilisateur);

INSERT INTO utilisateur (nom, prenom, email, mot_de_passe)
VALUES ('master admin', 'master admin', 'master.admin@hspjava.com', '$2b$12$dE2gA55jg.47yWxb06AAZ.WDInN/kG4zn3JpEohKV2juA5ZS5yXA2');
INSERT INTO admin (ref_utilisateur) VALUES ('1');
