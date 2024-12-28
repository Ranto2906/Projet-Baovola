CREATE TABLE Client(
   id_client VARCHAR(50)  default  'CLT'  || LPAD(nextval('client_id_seq')::text,5,'0'),
   nom VARCHAR(255)  NOT NULL,
   telephone VARCHAR(50)  NOT NULL,
   email VARCHAR(50) ,
   date_inscription DATE NOT NULL,
   PRIMARY KEY(id_client)
);

CREATE TABLE TypeOrdi(
   id_type_ordi VARCHAR(50)  default  'TYO'  || LPAD(nextval('type_ordi_id_seq')::text,5,'0'),
   val VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_type_ordi)
);

CREATE TABLE Systeme(
   id_systeme VARCHAR(50)  default  'SYS'  || LPAD(nextval('systeme_id_seq')::text,5,'0'),
   val VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_systeme)
);

CREATE TABLE TypeSpecialite(
   id_type_specialite VARCHAR(50)  default  'TYS'  || LPAD(nextval('type_specialite_id_seq')::text,5,'0'),
   val VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_type_specialite)
);

CREATE TABLE SalaireTechnicien(
   id_salaire_technicien VARCHAR(50)  default  'SAL'  || LPAD(nextval('salaire_technicien_id_seq')::text,5,'0'),
   salaire NUMERIC(18,2)   NOT NULL,
   date_salaire DATE NOT NULL,
   PRIMARY KEY(id_salaire_technicien)
);

CREATE TABLE TypeProbleme(
   id_type_probleme VARCHAR(50)  default  'TYR'  || LPAD(nextval('type_probleme_id_seq')::text,5,'0'),
   val VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_type_probleme)
);

CREATE TABLE TypeComposant(
   id_type_composant VARCHAR(50)  default  'TYC'  || LPAD(nextval('type_composant_id_seq')::text,5,'0'),
   val VARCHAR(255)  NOT NULL,
   description VARCHAR(255) ,
   PRIMARY KEY(id_type_composant)
);

CREATE TABLE Fournisseur(
   id_fournisseur VARCHAR(50)  default  'FOU'  || LPAD(nextval('fournisseur_id_seq')::text,5,'0'),
   val VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_fournisseur)
);

CREATE TABLE TypeReparation(
   id_type_reparation VARCHAR(50)  default  'TYR'  || LPAD(nextval('type_reparation_id_seq')::text,5,'0'),
   val VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_type_reparation)
);

CREATE TABLE Ordinateur(
   id_ordinateur VARCHAR(50)  default  'ORD'  || LPAD(nextval('ordinateur_id_seq')::text,5,'0'),
   marque VARCHAR(255)  NOT NULL,
   modele VARCHAR(255)  NOT NULL,
   id_systeme VARCHAR(50)  NOT NULL,
   id_type_ordi VARCHAR(50)  NOT NULL,
   id_client VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_ordinateur),
   FOREIGN KEY(id_systeme) REFERENCES Systeme(id_systeme),
   FOREIGN KEY(id_type_ordi) REFERENCES TypeOrdi(id_type_ordi),
   FOREIGN KEY(id_client) REFERENCES Client(id_client)
);

CREATE TABLE Technicien(
   id_technicien VARCHAR(50)  default  'TEC'  || LPAD(nextval('technicien_id_seq')::text,5,'0'),
   nom VARCHAR(255)  NOT NULL,
   telephone VARCHAR(50)  NOT NULL,
   email VARCHAR(50) ,
   date_embauche DATE NOT NULL,
   id_salaire_technicien VARCHAR(50) ,
   id_type_specialite VARCHAR(50) ,
   PRIMARY KEY(id_technicien),
   FOREIGN KEY(id_salaire_technicien) REFERENCES SalaireTechnicien(id_salaire_technicien),
   FOREIGN KEY(id_type_specialite) REFERENCES TypeSpecialite(id_type_specialite)
);

CREATE TABLE Reparation(
   id_reparation VARCHAR(50)  default  'REP'  || LPAD(nextval('reparation_id_seq')::text,5,'0'),
   date_reparation VARCHAR(50)  NOT NULL,
   description VARCHAR(255) ,
   status VARCHAR(1)  NOT NULL,
   id_ordinateur VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_reparation),
   FOREIGN KEY(id_ordinateur) REFERENCES Ordinateur(id_ordinateur)
);

CREATE TABLE Composant(
   id_composant VARCHAR(50)  default  'COM'  || LPAD(nextval('composant_id_seq')::text,5,'0'),
   nom VARCHAR(255)  NOT NULL,
   prix_achat NUMERIC(18,2)   NOT NULL,
   prix_vente NUMERIC(18,2)   NOT NULL,
   id_type_composant VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_composant),
   FOREIGN KEY(id_type_composant) REFERENCES TypeComposant(id_type_composant)
);

CREATE TABLE Stock(
   id_stock VARCHAR(50)  default  'STK'  || LPAD(nextval('stock_id_seq')::text,5,'0'),
   quantite_disponible INTEGER NOT NULL,
   id_composant VARCHAR(50) ,
   PRIMARY KEY(id_stock),
   FOREIGN KEY(id_composant) REFERENCES Composant(id_composant)
);

CREATE TABLE Achat(
   id_achat VARCHAR(50)  default  'ACH'  || LPAD(nextval('achat_id_seq')::text,5,'0'),
   date_achat DATE NOT NULL,
   quantite INTEGER NOT NULL,
   prix_unitaire NUMERIC(18,2)   NOT NULL,
   id_fournisseur VARCHAR(50) ,
   id_composant VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_achat),
   FOREIGN KEY(id_fournisseur) REFERENCES Fournisseur(id_fournisseur),
   FOREIGN KEY(id_composant) REFERENCES Composant(id_composant)
);

CREATE TABLE Probleme(
   id_probleme VARCHAR(50)  default  'PRO'  || LPAD(nextval('probleme_id_seq')::text,5,'0'),
   description VARCHAR(255) ,
   id_composant VARCHAR(50)  NOT NULL,
   id_type_probleme VARCHAR(50) ,
   PRIMARY KEY(id_probleme),
   FOREIGN KEY(id_composant) REFERENCES Composant(id_composant),
   FOREIGN KEY(id_type_probleme) REFERENCES TypeProbleme(id_type_probleme)
);

CREATE TABLE ReparationDetails(
   id_reparation_details VARCHAR(50)  default  'RED'  || LPAD(nextval('reparation_details_id_seq')::text,5,'0'),
   prix_reparation NUMERIC(18,2)   NOT NULL,
   quantite_utilisee SMALLINT,
   id_type_reparation VARCHAR(50)  NOT NULL,
   id_composant VARCHAR(50) ,
   id_technicien VARCHAR(50)  NOT NULL,
   id_probleme VARCHAR(50)  NOT NULL,
   id_reparation VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_reparation_details),
   FOREIGN KEY(id_type_reparation) REFERENCES TypeReparation(id_type_reparation),
   FOREIGN KEY(id_composant) REFERENCES Composant(id_composant),
   FOREIGN KEY(id_technicien) REFERENCES Technicien(id_technicien),
   FOREIGN KEY(id_probleme) REFERENCES Probleme(id_probleme),
   FOREIGN KEY(id_reparation) REFERENCES Reparation(id_reparation)
);

CREATE TABLE MvtStock(
   id_mvt_stock VARCHAR(50)  default  'MVT'  || LPAD(nextval('mouvement_stock_id_seq')::text,5,'0'),
   date_mouvement DATE NOT NULL,
   entree INTEGER NOT NULL,
   sortie INTEGER NOT NULL,
   type_mvt VARCHAR(10)  NOT NULL,
   id_achat VARCHAR(50) ,
   id_reparation_details VARCHAR(50) ,
   id_composant VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_mvt_stock),
   FOREIGN KEY(id_achat) REFERENCES Achat(id_achat),
   FOREIGN KEY(id_reparation_details) REFERENCES ReparationDetails(id_reparation_details),
   FOREIGN KEY(id_composant) REFERENCES Composant(id_composant)
);
