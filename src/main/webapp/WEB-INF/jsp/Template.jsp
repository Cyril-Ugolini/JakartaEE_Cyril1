<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HEADER COMPLET -->
<div id="tpl-header">
    <header class="crm-header" role="banner">

        <a class="crm-brand"
           href="${pageContext.request.contextPath}/FrontController?cmd=accueil">
            ENTREPRISE
        </a>

        <button class="crm-burger" id="burger-main" aria-expanded="false">
            <span></span><span></span><span></span>
        </button>

        <nav class="crm-nav-panel" id="panel-main">

            <!-- Clients -->
            <div class="crm-acc-item">
                <button class="crm-acc-toggle" data-target="acc-clients">
                    Clients <span class="crm-arrow"></span>
                </button>
                <div class="crm-acc-content" id="acc-clients">
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe">Liste des clients</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=clientForm">Créer un client</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe&mode=modifier">Modifier un client</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe&mode=supprimer">Supprimer un client</a>
                </div>
            </div>

            <!-- Prospects -->
            <div class="crm-acc-item">
                <button class="crm-acc-toggle" data-target="acc-prospects">
                    Prospects <span class="crm-arrow"></span>
                </button>
                <div class="crm-acc-content" id="acc-prospects">
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe">Liste des prospects</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectForm">Créer un prospect</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe&mode=modifier">Modifier un prospect</a>
                    <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe&mode=supprimer">Supprimer un prospect</a>
                </div>
            </div>

            <!-- Connexion -->
            <div class="crm-nav-connexion" id="nav-connexion"></div>

        </nav>
    </header>
</div>

<!-- HEADER RETOUR -->
<div id="tpl-header-retour">
    <header class="crm-header">
        <a class="crm-brand"
           href="${pageContext.request.contextPath}/FrontController?cmd=accueil">
            ENTREPRISE
        </a>
        <button class="crm-btn-retour" onclick="history.back()">← Retour</button>
    </header>
</div>

<!-- ASIDE -->
<div id="tpl-aside">
    <aside class="d-none d-md-block col-md-3">
        <div class="p-3 bg-light border rounded">
            <h5>Menu rapide</h5>
            <ul class="list-unstyled">
                <li><a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe">Clients</a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe">Prospects</a></li>
            </ul>
            <div id="aside-connexion"></div>
        </div>
    </aside>
</div>

<!-- FOOTER -->
<div id="tpl-footer">
    <footer class="footer-crm text-center border-top">
        <div class="container">
            <ul class="footer-links">
                <li>© 2026 CRM Formation AFPA – Cyril</li>
                <li><a href="FrontController?cmd=mentionsLegales">Mentions légales</a></li>
                <li><a href="FrontController?cmd=confidentialite">Politique de confidentialité</a></li>
                <li><a href="FrontController?cmd=confidentialite#droits">Vos droits (RGPD)</a></li>
            </ul>
        </div>
    </footer>
</div>
