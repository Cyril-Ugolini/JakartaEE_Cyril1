document.addEventListener('DOMContentLoaded', function () {

    // Si la page indique "pas de header", on ne charge rien
    if (document.body.dataset.header === "none") {
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

});
