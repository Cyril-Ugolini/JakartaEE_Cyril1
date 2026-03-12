/**
 * template.js
 * Version Jakarta EE – Cyril
 */

document.addEventListener('DOMContentLoaded', function () {

    // ─── Protection des pages ─────────────────────────────────────────────────────

    const pagesProtegees = [
        'client-form.jsp',
        'prospect-form.jsp',
        'delete-client.jsp',
        'delete-prospect.jsp'
    ];

    const pageCourante = window.location.pathname.split('/').pop();
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    const params = new URLSearchParams(window.location.search);
    const mode = params.get('mode');

    if (pagesProtegees.includes(pageCourante) && !isLoggedIn) {
        window.location.href = "FrontController?cmd=login";
        return;
    }

    if ((mode === 'modifier' || mode === 'supprimer') && !isLoggedIn) {
        window.location.href = "FrontController?cmd=login";
        return;
    }

    // ─── Chargement du template via FrontController ───────────────────────────────

    fetch(TEMPLATE_URL)
        .then(res => res.text())
        .then(html => {

            const parser = new DOMParser();
            const doc = parser.parseFromString(html, "text/html");

            const headerType = document.body.getAttribute("data-header");

            // HEADER
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

                if (headerType !== "retour") {
                    initBurgerMenu();
                }
            }

            // ASIDE
            const asideTarget = document.getElementById("aside");
            if (asideTarget) {
                const asideSource = doc.getElementById("tpl-aside");
                if (asideSource) {
                    asideTarget.replaceChildren(
                        document.adoptNode(asideSource.cloneNode(true))
                    );
                }
            }

            // FOOTER
            const footerTarget = document.getElementById("footer");
            if (footerTarget) {
                const footerSource = doc.getElementById("tpl-footer");
                if (footerSource) {
                    footerTarget.replaceChildren(
                        document.adoptNode(footerSource.cloneNode(true))
                    );
                }
            }

            // Mise à jour connexion
            gererConnexion();
        })
        .catch(err => {
            console.error("Erreur lors du chargement du template :", err);
        });

    // ─── Menu burger ─────────────────────────────────────────────────────────────

    function initBurgerMenu() {
        const burger = document.getElementById("burger-main");
        const panel = document.getElementById("panel-main");

        if (!burger || !panel) return;

        burger.addEventListener("click", () => {
            burger.classList.toggle("open");
            panel.classList.toggle("open");
        });

        document.addEventListener("click", (e) => {
            if (!burger.contains(e.target) && !panel.contains(e.target)) {
                burger.classList.remove("open");
                panel.classList.remove("open");
            }
        });

        panel.querySelectorAll(".crm-acc-toggle").forEach(btn => {
            btn.addEventListener("click", () => {
                const content = document.getElementById(btn.dataset.target);
                btn.classList.toggle("open");
                content.classList.toggle("open");
            });
        });
    }

    // ─── Gestion de la connexion ──────────────────────────────────────────────────

    function gererConnexion() {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        const username = localStorage.getItem('username') || '';

        // Menu burger
        const navConnexion = document.getElementById('nav-connexion');
        if (navConnexion) {
            navConnexion.replaceChildren();
            if (isLoggedIn) {
                const p = document.createElement('p');
                p.className = 'crm-username';
                p.textContent = '👤 ' + username;

                const a = document.createElement('a');
                a.href = 'FrontController?cmd=logout';
                a.className = 'crm-btn-connexion';
                a.textContent = 'Déconnexion';

                navConnexion.appendChild(p);
                navConnexion.appendChild(a);
            } else {
                const a = document.createElement('a');
                a.href = 'FrontController?cmd=login';
                a.className = 'crm-btn-connexion';
                a.textContent = 'Connexion';
                navConnexion.appendChild(a);
            }
        }

        // Aside
        const asideConnexion = document.getElementById('aside-connexion');
        if (asideConnexion) {
            asideConnexion.replaceChildren();
            if (isLoggedIn) {
                const p = document.createElement('p');
                p.className = 'mt-2 mb-1 small';
                p.textContent = '👤 ' + username;

                const a = document.createElement('a');
                a.href = 'FrontController?cmd=logout';
                a.className = 'btn btn-danger btn-sm w-100';
                a.textContent = 'Déconnexion';

                asideConnexion.appendChild(p);
                asideConnexion.appendChild(a);
            } else {
                const a = document.createElement('a');
                a.href = 'FrontController?cmd=login';
                a.className = 'btn btn-primary btn-sm w-100 mt-2';
                a.textContent = 'Connexion';
                asideConnexion.appendChild(a);
            }
        }

        // Masquage des boutons Modifier/Supprimer
        if (!isLoggedIn) {
            document.querySelectorAll('.btn-modifier, .btn-supprimer').forEach(btn => {
                btn.classList.add('d-none');
            });
        }
    }

});