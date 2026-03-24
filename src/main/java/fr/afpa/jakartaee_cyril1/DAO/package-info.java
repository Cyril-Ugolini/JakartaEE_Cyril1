/**
 * Fournit l'ensemble des classes d'accès aux données (DAO) de l'application.
 *
 * <p>Chaque DAO encapsule la logique d'interaction avec la base de données,
 * notamment les opérations CRUD (Create, Read, Update, Delete) pour les
 * différentes entités du modèle : Client, Prospect, Adresse et Contrat.</p>
 *
 * <p>Les DAO utilisent des requêtes SQL préparées, gèrent les exceptions
 * SQL de manière centralisée et assurent la cohérence des transactions.</p>
 */
package fr.afpa.jakartaee_cyril1.DAO;
