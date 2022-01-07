CREATE DATABASE  IF NOT EXISTS `j2e`;
USE `j2e`;

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prenom` varchar(60) NOT NULL,
  `mail` varchar(60) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `role` varchar(60) NOT NULL,
  `bloquer` tinyint DEFAULT '0',
  PRIMARY KEY (`id_client`)
)

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE `categorie` (
  `idCategorie` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(60) NOT NULL,
  `description` varchar(1000) NOT NULL,
  PRIMARY KEY (`idCategorie`),
  UNIQUE KEY `titre_UNIQUE` (`titre`)
)

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id_produit` int NOT NULL AUTO_INCREMENT,
  `nom_produit` varchar(60) NOT NULL,
  `description_produit` varchar(1000) NOT NULL,
  `prix_produit` int NOT NULL,
  `quantite_produit` int NOT NULL,
  `id_categorie` int NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `categorie_idx` (`id_categorie`),
  CONSTRAINT `categorie` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`idCategorie`)
)

DROP TABLE IF EXISTS `facture`;
CREATE TABLE `facture` (
  `id_facture` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `date` date NOT NULL,
  `pdf_name` varchar(45) NOT NULL,
  `pdf_file` mediumblob NOT NULL,
  PRIMARY KEY (`id_facture`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `client` (`id_client`)
)

INSERT INTO client (username, password, nom, prenom, mail, adresse, role, bloquer) 
VALUES ('admin','admin','Admin','Admin','admin@admin.fr','None','admin',0);