<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
                </div>
            </div>

            <!-- Connexion -->
            <div class="crm-nav-connexion" id="nav-connexion">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">

                        <!-- Icône utilisateur -->
                        <div class="crm-user-icon">
                            👤 <span>${sessionScope.user.username}</span>
                        </div>

                        <a class="crm-btn-connexion"
                           href="${pageContext.request.contextPath}/FrontController?cmd=logout">
                            Déconnexion
                        </a>

                    </c:when>

                    <c:otherwise>
                        <a class="crm-btn-connexion"
                           href="${pageContext.request.contextPath}/FrontController?cmd=login">
                            Connexion
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>

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
    <aside>
        <div class="p-3 bg-light border rounded">

            <!-- Icône utilisateur si connecté -->
            <c:if test="${not empty sessionScope.user}">
                <div class="aside-user-icon mb-3">
                    👤 <span>${sessionScope.user.username}</span>
                </div>
            </c:if>

            <h5>Menu rapide</h5>
            <ul class="list-unstyled">
                <li><a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe">Clients</a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe">Prospects</a></li>
            </ul>

            <div id="aside-connexion" class="d-flex justify-content-center mt-3">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a class="btn btn-primary px-3 py-1"
                           href="${pageContext.request.contextPath}/FrontController?cmd=logout">
                            Déconnexion
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-primary px-3 py-1"
                           href="${pageContext.request.contextPath}/FrontController?cmd=login">
                            Connexion
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>

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
