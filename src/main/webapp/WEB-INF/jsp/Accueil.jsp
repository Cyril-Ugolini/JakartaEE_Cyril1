<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ENTREPRISE - Accueil</title>

    <link href="${pageContext.request.contextPath}/node_modules/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>

<body data-header="full" class="home-page">

<!-- HEADER dynamique -->
<div id="header"></div>

<!-- ÉTOILES ANIMÉES -->
<div class="stars" id="stars"></div>

<!-- CERCLES TECH -->
<div class="tech-circle tech-circle-1"></div>
<div class="tech-circle tech-circle-2"></div>
<div class="tech-circle tech-circle-3"></div>

<!-- GRILLE -->
<div class="hero-grid"></div>

<!-- CONTENU CENTRÉ -->
<main class="home-content text-center" role="main">
    <h1 class="home-title">ENTREPRISE</h1>
    <p class="home-subtitle">Gérez vos clients et prospects<br>depuis une interface unique et intuitive.</p>

    <div class="home-buttons">
        <a href="${pageContext.request.contextPath}/FrontController?cmd=clientListe" class="btn-home">Clients</a>
        <a href="${pageContext.request.contextPath}/FrontController?cmd=prospectListe" class="btn-home">Prospects</a>
    </div>
</main>

<!-- FOOTER dynamique -->
<div id="footer"></div>

<!-- SCRIPTS -->
<script src="${pageContext.request.contextPath}/template.js"></script>
<script src="${pageContext.request.contextPath}/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

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