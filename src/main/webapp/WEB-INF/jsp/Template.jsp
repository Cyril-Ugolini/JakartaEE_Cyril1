<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- TEMPLATE GLOBAL -->

<!-- HEADER COMPLET (menu burger avec accordéons) -->
<div id="tpl-header">
    <header class="crm-header" role="banner">
        <a class="crm-brand" href="FrontController?cmd=accueil" aria-label="Accueil ENTREPRISE">ENTREPRISE</a>

        <button class="crm-burger" id="burger-main" aria-label="Ouvrir le menu" aria-expanded="false" aria-controls="panel-main">
            <span></span>
            <span></span>
            <span></span>
        </button>

        <nav class="crm-nav-panel" id="panel-main" aria-label="Menu principal">

            <!-- Clients -->
            <div class="crm-acc-item">
                <button class="crm-acc-toggle" data-target="acc-clients" aria-expanded="false" aria-controls="acc-clients">
                    Clients
                    <span class="crm-arrow" aria-hidden="true"></span>
                </button>
                <div class="crm-acc-content" id="acc-clients">
                    <a href="FrontController?cmd=clientListe">Liste des clients</a>
                    <a href="FrontController?cmd=clientForm" onclick="localStorage.removeItem('clientAModifier')">Créer un client</a>
                    <a href="FrontController?cmd=clientListe&mode=modifier">Modifier un client</a>
                    <a href="FrontController?cmd=clientListe&mode=supprimer">Supprimer un client</a>
                </div>
            </div>

            <!-- Prospects -->
            <div class="crm-acc-item">
                <button class="crm-acc-toggle" data-target="acc-prospects" aria-expanded="false" aria-controls="acc-prospects">
                    Prospects
                    <span class="crm-arrow" aria-hidden="true"></span>
                </button>
                <div class="crm-acc-content" id="acc-prospects">
                    <a href="FrontController?cmd=prospectListe">Liste des prospects</a>
                    <a href="FrontController?cmd=prospectForm" onclick="localStorage.removeItem('prospectAModifier')">Créer un prospect</a>
                    <a href="FrontController?cmd=prospectListe&mode=modifier">Modifier un prospect</a>
                    <a href="FrontController?cmd=prospectListe&mode=supprimer">Supprimer un prospect</a>
                </div>
            </div>

            <!-- Connexion / Déconnexion -->
            <div class="crm-nav-connexion" id="nav-connexion">
                <!-- Rempli dynamiquement par template.js -->
            </div>

        </nav>
    </header>
</div>


<!-- HEADER AVEC BOUTON RETOUR -->
<div id="tpl-header-retour">
    <header class="crm-header" role="banner">
        <a class="crm-brand" href="FrontController?cmd=accueil" aria-label="Accueil ENTREPRISE">ENTREPRISE</a>
        <button class="crm-btn-retour" onclick="history.back()" aria-label="Retour à la page précédente">← Retour</button>
    </header>
</div>


<!-- ASIDE (menu rapide) -->
<div id="tpl-aside">
    <aside class="d-none d-md-block col-md-3" role="complementary" aria-label="Menu rapide">
        <div class="p-3 bg-light border rounded">
            <h5>Menu rapide</h5>
            <ul class="list-unstyled">
                <li><a href="FrontController?cmd=clientListe">Clients</a></li>
                <li><a href="FrontController?cmd=prospectListe">Prospects</a></li>
            </ul>
            <div id="aside-connexion"></div>
        </div>
    </aside>
</div>


<!-- FOOTER -->
<div id="tpl-footer">
    <footer class="footer-crm text-center text-muted border-top" role="contentinfo">
        <div class="container">
            <ul class="footer-links list-inline mb-0" aria-label="Liens légaux">
                <li class="list-inline-item">© 2026 CRM Formation AFPA – Cyril</li>
                <li class="list-inline-item">
                    <a href="FrontController?cmd=mentionsLegales">Mentions légales</a>
                </li>
                <li class="list-inline-item">
                    <a href="FrontController?cmd=confidentialite">Politique de confidentialité</a>
                </li>
                <li class="list-inline-item">
                    <a href="FrontController?cmd=confidentialite#droits">Vos droits (RGPD)</a>
                </li>
            </ul>
        </div>
    </footer>
</div>