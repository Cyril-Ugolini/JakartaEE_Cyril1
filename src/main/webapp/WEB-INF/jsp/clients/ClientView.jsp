<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<!-- ============================================================================
FICHIER : ClientView.jsp
ROLE : Affiche la fiche détaillée d’un client
ARCHITECTURE : MVC (Jakarta EE)
============================================================================ -->

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Fiche client</title>

    <!-- Styles -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">
                Fiche client
            </h1>

            <!-- Si le client existe -->
            <c:if test="${not empty client}">

                <div class="card shadow-sm mb-4">
                    <div class="card-body">

                        <h4 class="card-title mb-3">
                            <c:out value="${client.raisonSociale}"/>
                        </h4>

                        <p><strong>Adresse :</strong>
                            <c:out value="${client.adresse.numeroRue}"/>
                            <c:out value="${client.adresse.nomRue}"/>
                        </p>

                        <p><strong>Ville :</strong>
                            <c:out value="${client.adresse.ville}"/>
                        </p>

                        <p><strong>Code postal :</strong>
                            <c:out value="${client.adresse.codePostal}"/>
                        </p>

                        <p><strong>Téléphone :</strong>
                            <c:out value="${client.telephone}"/>
                        </p>

                        <p><strong>Email :</strong>
                            <c:out value="${client.adresseMail}"/>
                        </p>

                        <p><strong>Chiffre d'affaires :</strong>
                            <c:out value="${client.chiffreAffaires}"/> €
                        </p>

                        <p><strong>Nombre d'employés :</strong>
                            <c:out value="${client.nombreEmployes}"/>
                        </p>

                        <div class="d-flex justify-content-between mt-4">
                            <a href="FrontController?cmd=clientListe" class="btn btn-secondary">
                                Retour
                            </a>

                            <a href="FrontController?cmd=clientForm&mode=modifier&idClient=${client.idClient}"
                               class="btn btn-warning">
                                Modifier
                            </a>
                        </div>

                    </div>
                </div>

                <!-- Carte Leaflet -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h5>Localisation</h5>
                        <div id="carte"></div>
                    </div>
                </div>

                <!-- Météo -->
                <div class="card" id="meteo-card">
                    <div class="card-body">
                        <h5>Météo actuelle</h5>
                        <div id="meteo-contenu">Chargement...</div>
                    </div>
                </div>

            </c:if>

            <!-- Si aucun client trouvé -->
            <c:if test="${empty client}">
                <div class="alert alert-warning">
                    Client introuvable.
                </div>
            </c:if>

        </main>

        <div id="tpl-aside" class="col-md-3"></div>

    </div>
</div>

<div id="tpl-footer"></div>

<!-- Scripts -->
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<!-- ⚠️ AJOUT IMPORTANT : validation.js AVANT template.js -->
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

<!-- Initialisation carte + météo -->
<c:if test="${not empty client}">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            window.initCarteMeteo(
                "${client.adresse.numeroRue} ${client.adresse.nomRue}",
                "${client.adresse.codePostal}",
                "${client.adresse.ville}",
                "${client.raisonSociale}",
                ""
            );
        });
    </script>
</c:if>

</body>
</html>
