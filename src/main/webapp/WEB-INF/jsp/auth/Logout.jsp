<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Déconnexion</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Ton CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<!-- HEADER dynamique -->
<div id="tpl-header"></div>

<!-- CONTENU -->
<div class="login-page">
    <div class="card p-4 text-center">
        <h1 class="mb-3">Déconnexion</h1>
        <p class="mb-4">Voulez-vous vraiment vous déconnecter ?</p>

        <button class="btn btn-danger w-100" id="btn-deconnexion">Oui, me déconnecter</button>

        <!-- Annuler → retour à l'accueil -->
        <a class="btn btn-secondary w-100 mt-3"
           href="${pageContext.request.contextPath}/FrontController?cmd=accueil">
            Annuler
        </a>
    </div>
</div>

<!-- FOOTER dynamique -->
<div id="tpl-footer"></div>

<!-- URL du template -->
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<!-- SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {

        document.getElementById('btn-deconnexion').addEventListener('click', function () {

            // Suppression des infos de connexion
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('username');

            // Redirection vers l'accueil
            window.location.href = "${pageContext.request.contextPath}/FrontController?cmd=accueil";
        });

    });
</script>

</body>
</html>