
 CRM – Gestion des Clients & Prospects
Projet Java EE – AFPA 2025–2026
Développé par Cyril

 1. Présentation générale
Ce projet CRM a été réalisé dans le cadre de la formation Développeur Java – AFPA.
Il permet la gestion :
• 	des clients
• 	des prospects
• 	des sociétés
L’application démontre la maîtrise :
• 	de l’architecture MVC
• 	des Servlets, JSP, JSTL
• 	de la sécurité (sessions, CSRF, hachage avec sel + poivre)
• 	de la programmation orientée objet
• 	d’une interface moderne et responsive
• 	d’un workflow professionnel (Git, Maven, Tomcat)
---
 2. Compétences démontrées
 Architecture logicielle
• 	FrontController centralisé
• 	Séparation Modèle / Vue / Contrôleur
• 	DAO + Services
• 	Beans métiers cohérents (Client, Prospect, Société, Adresse)
 Développement Java EE
• 	Servlets
• 	JSP + JSTL + EL
• 	Validation des données
• 	Gestion des erreurs et messages utilisateur
 Sécurité
• 	Authentification avec hachage + sel + poivre
• 	Sessions sécurisées
• 	Accès restreint selon connexion
• 	Protection CSRF sur tous les formulaires POST
• 	Encodage via  pour éviter les injections XSS
️ Base de données
• 	Schéma relationnel
• 	DataSource pour la connexion
• 	Requêtes SQL propres et sécurisées
• 	Gestion des exceptions SQL
 UI / UX
• 	Thème dark moderne
• 	Design responsive
• 	Templates réutilisables (header, aside, footer)
• 	Icône utilisateur dynamique
• 	Navigation claire et intuitive
 Outils professionnels
• 	Maven
• 	Tomcat 10
• 	Git / GitHub
• 	IntelliJ IDEA
• 	MySQL
• 	Checkstyle
---
 3. Architecture technique
3.1. Organisation du projet

3.2. FrontController
Toutes les actions passent par :
FrontController?cmd=action
---
 4. Modèle métier
Société
• 	id
• 	raison sociale
• 	adresse
• 	téléphone
• 	type (client / prospect)
Client
• 	idClient
• 	raison sociale
• 	adresse
• 	téléphone
• 	chiffre d’affaires
• 	nombre d’employés
Prospect
• 	idProspect
• 	raison sociale
• 	adresse
• 	téléphone
• 	date de prospection
• 	intérêt
---
 5. Sécurité
5.1. Authentification
• 	Formulaire de connexion
• 	Hachage sécurisé (sel + poivre)
• 	Stockage de l’utilisateur en session

5.2. Autorisation
• 	Non connecté → accès uniquement à la liste des clients
• 	Connecté → accès complet (CRUD)

5.3. Protection CSRF
Chaque formulaire POST inclut :

5.4. Sécurisation des données
• 	Validation côté serveur
• 	Validation côté client
• 	Encodage via
---
 6. Interface utilisateur
• 	Thème dark moderne
• 	Couleurs cyan / bleu électrique
• 	Icône utilisateur dans header + aside
• 	Menu responsive (burger)
• 	Aside sticky sur desktop
• 	Templates injectés via
---
 7. Fonctionnalités principales
 Clients
• 	Liste
• 	Création
• 	Modification
• 	Suppression
• 	Consultation
 Prospects
• 	Liste
• 	Création
• 	Modification
• 	Suppression
• 	Consultation
 Authentification
• 	Connexion
• 	Déconnexion
• 	Affichage dynamique
---
 8. Technologies utilisées
• 	Java 17
• 	JSP / JSTL / Servlets
• 	Maven
• 	Tomcat 10
• 	MySQL
• 	Bootstrap 5
• 	CSS custom
• 	Git / GitHub
• 	Checkstyle
---
 9. Installation & exécution
1. Cloner le projet

2. Configurer la base MySQL
   Modifier  :

3. Lancer Tomcat
   Déployer via IntelliJ ou Maven.
4. Accéder à l’application
---

 10. Critères d’évaluation (AFPA)
• 	pom.xml
• 	web.xml
• 	Fichier de persistance
• 	JSP
• 	FrontController
• 	Contrôleurs
• 	Beans
• 	Logs
• 	DAO
• 	Tokens CSRF
• 	Authentification (hachage + sel + poivre + sessions)
• 	Gestion des exceptions
• 	Javadoc + commentaires techniques
• 	Qualité du code (Checkstyle)
---
 11. Points forts du projet
• 	Architecture MVC maîtrisée
• 	Sécurité CSRF + hachage avancé
• 	Interface moderne et responsive
• 	Code propre, structuré, maintenable
• 	CRUD complet
• 	Templates JSP réutilisables
• 	Workflow Git professionnel
• 	Projet prêt pour un environnement réel
---
 12. Auteur
Projet réalisé par Cyril,
dans le cadre de la formation Développeur Java – AFPA 2025–2026
