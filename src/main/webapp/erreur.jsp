<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Erreur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body data-header="retour">

<div id="tpl-header"></div>

<div class="login-page">
    <div class="card p-4 text-center">

        <%
            Integer statusCode = (Integer) request.getAttribute(
                    "jakarta.servlet.error.status_code");
            String message = (String) request.getAttribute(
                    "jakarta.servlet.error.message");
        %>

        <h1 class="mb-3" style="font-size: 4rem; color: #e57373;">
            <%= statusCode != null ? statusCode : "Erreur" %>
        </h1>

        <h4 class="mb-3">
            <% if (statusCode != null && statusCode == 404) { %>
            Page introuvable
            <% } else if (statusCode != null && statusCode == 500) { %>
            Erreur interne du serveur
            <% } else { %>
            Une erreur est survenue
            <% } %>
        </h4>

        <p class="text-muted mb-4">
            <%= message != null && !message.isEmpty()
                    ? message
                    : "La page demandée est introuvable ou une commande est inconnue." %>
        </p>

        <a href="${pageContext.request.contextPath}/FrontController?cmd=accueil"
           class="btn btn-primary">
            Retour à l'accueil
        </a>
    </div>
</div>

<div id="tpl-footer"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
