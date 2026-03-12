document.addEventListener('DOMContentLoaded', function () {

    // Pages protégées
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

    // Chargement du template
    fetch(TEMPLATE_URL)
        .then(res => res.text())
        .then(html => {

            const parser = new DOMParser();
            const doc = parser.parseFromString(html, "text/html");

            const headerType = document.body.getAttribute("data-header");

            // HEADER
            const headerTarget = document.getElementById("tpl-header");
            if (headerTarget) {
                const headerSource = headerType === "retour"
                    ? doc.getElementById("tpl-header-retour")
                    : doc.getElementById("tpl-header");

                headerTarget.replaceChildren(
                    document.adoptNode(headerSource.cloneNode(true))
                );

                if (headerType !== "retour") initBurgerMenu();
            }

            // ASIDE
            const asideTarget = document.getElementById("tpl-aside");
            if (asideTarget) {
                const asideSource = doc.getElementById("tpl-aside");
                asideTarget.replaceChildren(
                    document.adoptNode(asideSource.cloneNode(true))
                );
            }

            // FOOTER
            const footerTarget = document.getElementById("tpl-footer");
            if (footerTarget) {
                const footerSource = doc.getElementById("tpl-footer");
                footerTarget.replaceChildren(
                    document.adoptNode(footerSource.cloneNode(true))
                );
            }

            gererConnexion();
        });

    // Menu burger
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

    // Connexion
    function gererConnexion() {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        const username = localStorage.getItem('username') || '';

        // Menu burger
        const navConnexion = document.getElementById('nav-connexion');
        if (navConnexion) {
            navConnexion.replaceChildren();
            if (isLoggedIn) {
                navConnexion.innerHTML = `
                    <p class="crm-username">👤 ${username}</p>
                    <a href="FrontController?cmd=logout" class="crm-btn-connexion">Déconnexion</a>
                `;
            } else {
                navConnexion.innerHTML = `
                    <a href="FrontController?cmd=login" class="crm-btn-connexion">Connexion</a>
                `;
            }
        }

        // Aside
        const asideConnexion = document.getElementById('aside-connexion');
        if (asideConnexion) {
            asideConnexion.replaceChildren();
            if (isLoggedIn) {
                asideConnexion.innerHTML = `
                    <p class="mt-2 mb-1 small">👤 ${username}</p>
                    <a href="FrontController?cmd=logout" class="btn btn-danger btn-sm w-100">Déconnexion</a>
                `;
            } else {
                asideConnexion.innerHTML = `
                    <a href="FrontController?cmd=login" class="btn btn-primary btn-sm w-100 mt-2">Connexion</a>
                `;
            }
        }
    }

});