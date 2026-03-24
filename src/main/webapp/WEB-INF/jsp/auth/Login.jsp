<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Connexion</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<!-- IMPORTANT : pas de header dynamique sur la page de connexion -->
<body data-header="none">

<div class="login-page">
    <div class="card p-4">

        <h1 class="mb-4 text-center">Connexion</h1>

        <!-- Formulaire POST vers LoginController -->
        <form id="login-form"
              method="post"
              action="${pageContext.request.contextPath}/FrontController?cmd=login"
              novalidate>

            <div class="mb-3">
                <label for="login-user" class="form-label">Utilisateur *</label>
                <input type="text" id="login-user" name="username" class="form-control"
                       placeholder="Votre identifiant" required>
                <div class="invalid-feedback">Identifiant obligatoire.</div>
            </div>

            <div class="mb-3">
                <label for="login-password" class="form-label">Mot de passe *</label>
                <input type="password" id="login-password" name="password" class="form-control"
                       placeholder="Votre mot de passe" required minlength="6">
                <div class="invalid-feedback">Mot de passe obligatoire (6 caracteres minimum).</div>
            </div>

            <!-- Message d'erreur renvoye par LoginController -->
            <c:if test="${not empty loginError}">
                <div class="alert alert-danger">${loginError}</div>
            </c:if>

            <button type="submit" class="btn btn-primary w-100 mt-2">Se connecter</button>
        </form>

    </div>
</div>

<!-- Pas de header dynamique ici -->
<div id="tpl-footer"></div>

<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {

        const form = document.getElementById('login-form');

        form.addEventListener('submit', function (event) {

            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                form.classList.add('was-validated');
            }
        });

    });
</script>

</body>
</html>
