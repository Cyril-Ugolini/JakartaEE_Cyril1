<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Mentions légales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container py-5">
    <main>
        <h1 class="mb-4">Mentions légales</h1>

        <section class="mb-4">
            <h2 class="h5">Éditeur du site</h2>
            <p>
                Ce site est un projet pédagogique réalisé dans le cadre de la formation Concepteur Développeur d'Application à l'AFPA.<br>
                <strong>Auteur :</strong> Cyril<br>
                <strong>Formation :</strong> AFPA – Concepteur Développeur d'Application<br>
                <strong>Année :</strong> 2025-2026
            </p>
        </section>

        <section class="mb-4">
            <h2 class="h5">Hébergement</h2>
            <p>Ce site est hébergé en local à des fins de développement et de formation. Aucune donnée n'est transmise à un serveur externe.</p>
        </section>

        <section class="mb-4">
            <h2 class="h5">Propriété intellectuelle</h2>
            <p>L'ensemble des contenus présents sur ce site (textes, code, structure) est produit dans un cadre pédagogique.
                Toute reproduction à des fins commerciales est interdite sans autorisation.</p>
        </section>

        <section class="mb-4">
            <h2 class="h5">Données personnelles</h2>
            <p>
                Les données saisies dans ce CRM sont fictives et destinées uniquement à des fins de démonstration.
            </p>
        </section>

        <a href="${pageContext.request.contextPath}/FrontController?cmd=accueil" class="btn btn-secondary mt-3">
            Retour à l'accueil
        </a>
    </main>
</div>

<div id="tpl-footer"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
