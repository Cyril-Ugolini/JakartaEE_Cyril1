<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ENTREPRISE - Accueil</title>

    <!-- Bootstrap CSS (CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Ton style perso -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>

<body data-header="full" class="home-page">

<!-- HEADER dynamique -->
<div id="tpl-header"></div>

<!-- Décorations -->
<div class="stars" id="stars"></div>
<div class="tech-circle tech-circle-1"></div>
<div class="tech-circle tech-circle-2"></div>
<div class="tech-circle tech-circle-3"></div>
<div class="hero-grid"></div>

<!-- Contenu principal -->
<main class="home-content text-center">
    <h1 class="home-title">ENTREPRISE</h1>
    <p class="home-subtitle" style="text-align:center; width:100%;">
        Gérez vos clients et prospects<br>
        depuis une interface unique et intuitive.
    </p>

    <div class="home-buttons">
        <a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe" class="btn-home">Clients</a>
        <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe" class="btn-home">Prospects</a>
    </div>
</main>

<!-- FOOTER dynamique -->
<div id="tpl-footer"></div>

<!-- URL du template -->
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<!-- Template dynamique -->
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

<!-- Bootstrap JS (CDN) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Étoiles -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const starsContainer = document.getElementById('stars');
        for (let i = 0; i < 120; i++) {
            const star = document.createElement('div');
            star.className = 'star';
            star.style.left = Math.random() * 100 + '%';
            star.style.top = Math.random() * 100 + '%';
            const size = Math.random() * 2.5 + 0.5;
            star.style.width = size + 'px';
            star.style.height = size + 'px';
            star.style.animationDelay = Math.random() * 4 + 's';
            star.style.animationDuration = (Math.random() * 3 + 2) + 's';
            starsContainer.appendChild(star);
        }
    });
</script>

</body>
</html>
