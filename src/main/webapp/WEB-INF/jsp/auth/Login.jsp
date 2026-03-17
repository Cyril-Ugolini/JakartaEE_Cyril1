<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Connexion</title>

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
    <div class="card p-4">
        <h1 class="mb-4 text-center">Connexion</h1>

        <form id="login-form" novalidate>
            <div class="mb-3">
                <label for="login-user" class="form-label">Utilisateur <span class="text-danger">*</span></label>
                <input type="text" id="login-user" name="username" class="form-control"
                       placeholder="Votre identifiant" required>
                <div class="invalid-feedback">L'identifiant est obligatoire.</div>
            </div>

            <div class="mb-3">
                <label for="login-password" class="form-label">Mot de passe <span class="text-danger">*</span></label>
                <input type="password" id="login-password" name="password" class="form-control"
                       placeholder="Votre mot de passe" required minlength="6">
                <div class="invalid-feedback">Le mot de passe est obligatoire (6 caractères minimum).</div>
            </div>

            <div id="login-error" class="alert alert-danger d-none" role="alert"></div>

            <button type="submit" class="btn btn-primary w-100 mt-2">Se connecter</button>
        </form>
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

        // Si déjà connecté → redirection vers l'accueil
        if (localStorage.getItem('isLoggedIn') === 'true') {
            window.location.href = "${pageContext.request.contextPath}/FrontController?cmd=accueil";
        }

        // Compte admin fictif (ECF)
        const ADMIN = {
            username: 'admin',
            password: 'password123'
        };

        const form = document.getElementById('login-form');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            const errorDiv = document.getElementById('login-error');
            errorDiv.classList.add('d-none');

            const html5Valid = form.checkValidity();
            form.classList.add('was-validated');

            if (!html5Valid) {
                errorDiv.textContent = 'Veuillez renseigner votre identifiant et votre mot de passe.';
                errorDiv.classList.remove('d-none');
                return;
            }

            const username = document.getElementById('login-user').value;
            const password = document.getElementById('login-password').value;

            if (username === ADMIN.username && password === ADMIN.password) {
                localStorage.setItem('isLoggedIn', 'true');
                localStorage.setItem('username', username);

                window.location.href = "${pageContext.request.contextPath}/FrontController?cmd=accueil";
            } else {
                errorDiv.textContent = 'Identifiant ou mot de passe incorrect.';
                errorDiv.classList.remove('d-none');
            }
        });

    });
</script>

</body>
</html>