<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Prospect" %>
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

<%
    Prospect prospect = (Prospect) request.getAttribute("prospect");
%>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Fiche prospect</h1>

            <% if (prospect != null) { %>
            <div class="card shadow-sm mb-4">
                <div class="card-body">

                    <h4 class="card-title mb-3">
                        <%= prospect.getRaisonSociale() %>
                    </h4>

                    <p><strong>Adresse :</strong>
                        <%= prospect.getAdresse().getNumeroRue() %>
                        <%= prospect.getAdresse().getNomRue() %>
                    </p>
                    <p><strong>Ville :</strong>
                        <%= prospect.getAdresse().getVille() %>
                    </p>
                    <p><strong>Code postal :</strong>
                        <%= prospect.getAdresse().getCodePostal() %>
                    </p>
                    <p><strong>Téléphone :</strong>
                        <%= prospect.getTelephone() %>
                    </p>
                    <p><strong>Email :</strong>
                        <%= prospect.getAdresseMail() %>
                    </p>
                    <p><strong>Date de prospection :</strong>
                        <%= prospect.getDateProspection() %>
                    </p>
                    <p><strong>Intéressé :</strong>
                        <%= prospect.getInteresse() %>
                    </p>

                    <div class="d-flex justify-content-between mt-4">
                        <a href="FrontController?cmd=prospectListe"
                           class="btn btn-secondary">Retour</a>
                        <a href="FrontController?cmd=prospectForm&mode=modifier&id=<%= prospect.getIdProspect() %>"
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

            <% } else { %>
            <div class="alert alert-warning">Prospect introuvable.</div>
            <% } %>

        </main>

        <div id="tpl-aside" class="col-md-3 d-none d-md-block"></div>

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

<% if (prospect != null) { %>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        window.initCarteMeteo(
            '<%= prospect.getAdresse().getNumeroRue().replace("'", "\\'") %> <%= prospect.getAdresse().getNomRue().replace("'", "\\'") %>',
            '<%= prospect.getAdresse().getCodePostal() %>',
            '<%= prospect.getAdresse().getVille() %>',
            '<%= prospect.getRaisonSociale().replace("'", "\\'").replace("&", "et") %>',
            ''
        );
    });
</script>
<% } %>

</body>
</html>
