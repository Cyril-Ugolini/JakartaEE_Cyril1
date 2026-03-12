/**
 * template.js
 * Gestion dynamique du header, footer et aside via fetch().
 * Gestion de la connexion/déconnexion et protection des pages.
 *
 * Fonctionnement :
 *  1. Vérifie si la page est protégée (nécessite connexion)
 *  2. Charge template.html via fetch()
 *  3. Injecte le header, aside et footer dans la page courante
 *  4. Initialise le menu burger et la gestion de connexion
 *
 * Pages protégées (redirection vers login.html si non connecté) :
 *  - client-form.html
 *  - prospect-form.html
 *  - delete-client.html
 *  - delete-prospect.html
 *  - Modes modifier/supprimer dans les listes
 *
 * @author Cyril
 * @version 1.0
 */

document.addEventListener('DOMContentLoaded', function () {

    // ─── Protection des pages ─────────────────────────────────────────────────────

    /**
     * Liste des pages nécessitant une connexion.
     * Toute tentative d'accès sans être connecté redirige vers login.html.
     * @type {string[]}
     */
    const pagesProtegees = [
        'client-form.html',
        'prospect-form.html',
        'delete-client.html',
        'delete-prospect.html'
    ];

    // Récupération de la page courante et de l'état de connexion
    const pageCourante = window.location.pathname.split('/').pop();
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    // Lecture du mode dans l'URL (?mode=modifier ou ?mode=supprimer)
    const params = new URLSearchParams(window.location.search);
    const mode = params.get('mode');

    // Redirection si page protégée et non connecté
    if (pagesProtegees.includes(pageCourante) && !isLoggedIn) {
        window.location.href = 'login.html';
        return;
    }

    // Redirection si mode modifier/supprimer et non connecté
    if ((mode === 'modifier' || mode === 'supprimer') && !isLoggedIn) {
        window.location.href = 'login.html';
        return;
    }

    // ─── Chargement du template ───────────────────────────────────────────────────

    /**
     * Charge template.html via fetch() et injecte les composants dans la page.
     * Utilise DOMParser pour parser le HTML et replaceChildren() pour
     * une injection sécurisée sans innerHTML.
     */
    fetch("template.html")
        .then(res => res.text())
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, "text/html");

            // Lecture du type de header depuis data-header sur le body
            // "full"   → header avec menu burger
            // "retour" → header simplifié avec bouton retour
            const headerType = document.body.getAttribute("data-header");

            // Injection sécurisée du header
            const headerTarget = document.getElementById("header");
            if (headerTarget) {
                const headerSource = headerType === "retour"
                    ? doc.getElementById("tpl-header-retour")
                    : doc.getElementById("tpl-header");

                if (headerSource) {
                    headerTarget.replaceChildren(
                        document.adoptNode(headerSource.cloneNode(true))
                    );
                }

                // Initialisation du burger uniquement sur le header complet
                if (headerType !== "retour") {
                    initBurgerMenu();
                }
            }

            // Injection sécurisée du aside
            const asideTarget = document.getElementById("aside");
            if (asideTarget) {
                const asideSource = doc.getElementById("tpl-aside");
                if (asideSource) {
                    asideTarget.replaceChildren(
                        document.adoptNode(asideSource.cloneNode(true))
                    );
                }
            }

            // Injection sécurisée du footer
            const footerTarget = document.getElementById("footer");
            if (footerTarget) {
                const footerSource = doc.getElementById("tpl-footer");
                if (footerSource) {
                    footerTarget.replaceChildren(
                        document.adoptNode(footerSource.cloneNode(true))
                    );
                }
            }

            // Mise à jour de l'état de connexion après injection
            gererConnexion();
        })
        .catch(err => {
            console.error("Erreur lors du chargement du template :", err);
        });

    // ─── Menu burger ─────────────────────────────────────────────────────────────

    /**
     * Initialise le menu burger et les accordéons de navigation.
     *
     * Comportements :
     *  - Clic sur le burger → ouvre/ferme le panel de navigation
     *  - Clic en dehors du panel → ferme le panel
     *  - Clic sur un accordéon → ouvre/ferme la section correspondante
     */
    function initBurgerMenu() {
        const burger = document.getElementById("burger-main");
        const panel = document.getElementById("panel-main");

        if (!burger || !panel) return;

        // Ouverture/fermeture du panel au clic sur le burger
        burger.addEventListener("click", () => {
            burger.classList.toggle("open");
            panel.classList.toggle("open");
        });

        // Fermeture du panel au clic en dehors
        document.addEventListener("click", (e) => {
            if (!burger.contains(e.target) && !panel.contains(e.target)) {
                burger.classList.remove("open");
                panel.classList.remove("open");
            }
        });

        // Gestion des accordéons (Clients / Prospects)
        panel.querySelectorAll(".crm-acc-toggle").forEach(btn => {
            btn.addEventListener("click", () => {
                const content = document.getElementById(btn.dataset.target);
                btn.classList.toggle("open");
                content.classList.toggle("open");
            });
        });
    }

    // ─── Gestion de la connexion ──────────────────────────────────────────────────

    /**
     * Met à jour l'affichage selon l'état de connexion.
     *
     * Actions :
     *  - Si connecté   → affiche le nom d'utilisateur + bouton Déconnexion
     *  - Si déconnecté → affiche le bouton Connexion
     *  - Dans le menu burger (nav-connexion) et dans le aside (aside-connexion)
     *  - Masque les boutons Modifier/Supprimer si non connecté
     *
     * Données lues depuis le localStorage :
     *  - isLoggedIn : 'true' ou 'false'
     *  - username   : nom de l'utilisateur connecté
     */
    function gererConnexion() {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        const username = localStorage.getItem('username') || '';

        // --- Bouton dans le menu burger ---
        const navConnexion = document.getElementById('nav-connexion');
        if (navConnexion) {
            navConnexion.replaceChildren();
            if (isLoggedIn) {
                // Affichage du nom d'utilisateur
                const p = document.createElement('p');
                p.className = 'crm-username';
                p.textContent = '👤 ' + username;

                // Bouton Déconnexion
                const a = document.createElement('a');
                a.href = 'logout.html';
                a.className = 'crm-btn-connexion';
                a.textContent = 'Déconnexion';

                navConnexion.appendChild(p);
                navConnexion.appendChild(a);
            } else {
                // Bouton Connexion
                const a = document.createElement('a');
                a.href = 'login.html';
                a.className = 'crm-btn-connexion';
                a.textContent = 'Connexion';
                navConnexion.appendChild(a);
            }
        }

        // --- Bouton dans le aside ---
        const asideConnexion = document.getElementById('aside-connexion');
        if (asideConnexion) {
            asideConnexion.replaceChildren();
            if (isLoggedIn) {
                // Affichage du nom d'utilisateur
                const p = document.createElement('p');
                p.className = 'mt-2 mb-1 small';
                p.textContent = '👤 ' + username;

                // Bouton Déconnexion
                const a = document.createElement('a');
                a.href = 'logout.html';
                a.className = 'btn btn-danger btn-sm w-100';
                a.textContent = 'Déconnexion';

                asideConnexion.appendChild(p);
                asideConnexion.appendChild(a);
            } else {
                // Bouton Connexion
                const a = document.createElement('a');
                a.href = 'login.html';
                a.className = 'btn btn-primary btn-sm w-100 mt-2';
                a.textContent = 'Connexion';
                asideConnexion.appendChild(a);
            }
        }

        // --- Masquage des boutons Modifier/Supprimer si non connecté ---
        if (!isLoggedIn) {
            document.querySelectorAll('.btn-modifier, .btn-supprimer').forEach(btn => {
                btn.classList.add('d-none');
            });
        }
    }

});
