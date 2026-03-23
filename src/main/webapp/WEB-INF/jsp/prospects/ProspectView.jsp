<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Fiche prospect</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Fiche prospect</h1>

            <!-- Si prospect trouvé -->
            <c:if test="${not empty prospect}">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">

                        <h4 class="card-title mb-3">
                            <c:out value="${prospect.raisonSociale}" />
                        </h4>

                        <p><strong>Adresse :</strong>
                            <c:out value="${prospect.adresse.numeroRue}" />
                            <c:out value="${prospect.adresse.nomRue}" />
                        </p>

                        <p><strong>Ville :</strong>
                            <c:out value="${prospect.adresse.ville}" />
                        </p>

                        <p><strong>Code postal :</strong>
                            <c:out value="${prospect.adresse.codePostal}" />
                        </p>

                        <p><strong>Téléphone :</strong>
                            <c:out value="${prospect.telephone}" />
                        </p>

                        <p><strong>Email :</strong>
                            <c:out value="${prospect.adresseMail}" />
                        </p>

                        <p><strong>Date de prospection :</strong>
                            <c:out value="${prospect.dateProspection}" />
                        </p>

                        <p><strong>Intéressé :</strong>
                            <c:out value="${prospect.interesse}" />
                        </p>

                        <div class="d-flex justify-content-between mt-4">
                            <a href="FrontController?cmd=prospectListe" class="btn btn-secondary">Retour</a>
                            <a href="FrontController?cmd=prospectForm&mode=modifier&id=${prospect.idProspect}"
                               class="btn btn-warning">Modifier</a>
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

            <!-- Si prospect introuvable -->
            <c:if test="${empty prospect}">
                <div class="alert alert-warning">Prospect introuvable.</div>
            </c:if>

        </main>

        <div id="tpl-aside" class="col-md-3"></div>

    </div>
</div>

<div id="tpl-footer"></div>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>

<!-- Initialisation carte + météo -->
<c:if test="${not empty prospect}">
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            window.initCarteMeteo(
                '${prospect.adresse.numeroRue.replace("'", "\\'")}'
                + ' '
                + '${prospect.adresse.nomRue.replace("'", "\\'")}',
                '${prospect.adresse.codePostal}',
                '${prospect.adresse.ville}',
                '${prospect.raisonSociale.replace("'", "\\'").replace("&", "et")}',
                ''
            );
        });
    </script>
</c:if>

</body>
</html>
