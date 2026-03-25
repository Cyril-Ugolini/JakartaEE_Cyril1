<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Deconnexion</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<!-- IMPORTANT : pas de header dynamique sur la page de deconnexion -->
<body data-header="none">

<div class="login-page">
    <div class="card p-4 text-center">

        <h1 class="mb-3">Deconnexion</h1>

        <%-- Affichage du nom de l'utilisateur connecté --%>
        <p class="mb-4">
            Voulez-vous vraiment vous deconnecter,
            <strong><c:out value="${sessionScope.user.username}"/></strong> ?
        </p>

        <!-- Formulaire POST vers LogoutController -->
        <form method="post"
              action="${pageContext.request.contextPath}/FrontController?cmd=logout">
            <button type="submit" class="btn btn-danger w-100">
                Oui, me deconnecter
            </button>
        </form>

        <a class="btn btn-secondary w-100 mt-3"
           href="${pageContext.request.contextPath}/FrontController?cmd=accueil">
            Annuler
        </a>

    </div>
</div>

<div id="tpl-footer"></div>

<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></strong></script>

</body>
</html>
