<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Fiche prospect</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- HEADER -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="FrontController">CRM</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Clients</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="FrontController?cmd=clientListe">Liste</a></li>
                        <li><a class="dropdown-item" href="FrontController?cmd=clientForm">Créer</a></li>
                        <li><a class="dropdown-item" href="FrontController?cmd=clientSuppression">Supprimer</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Prospects</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="FrontController?cmd=prospectListe">Liste</a></li>
                        <li><a class="dropdown-item" href="FrontController?cmd=prospectForm">Créer</a></li>
                        <li><a class="dropdown-item" href="FrontController?cmd=prospectSuppression">Supprimer</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- LAYOUT -->
<div class="container py-4">
    <div class="row">

        <!-- CONTENU PRINCIPAL -->
        <main class="col-12 col-md-9">
            <h1 class="mb-4">Fiche prospect</h1>

            <div class="card mb-4">
                <div class="card-body">

                    <h4 class="card-title mb-3">Nom Prénom</h4>

                    <p><strong>Adresse :</strong> </p>
                    <p><strong>Ville :</strong> </p>
                    <p><strong>Code postal :</strong> </p>
                    <p><strong>Téléphone :</strong> </p>
                    <p><strong>Email :</strong> </p>
                    <p><strong>Nombre d'employés :</strong> </p>
                    <p><strong>Chiffre d'affaires :</strong>  €</p>
                    <p><strong>Intérêt :</strong> </p>

                    <div class="d-flex justify-content-between mt-4">
                        <a href="FrontController?cmd=prospectListe" class="btn btn-secondary">Retour</a>
                        <a href="FrontController?cmd=prospectForm" class="btn btn-warning">Modifier</a>
                    </div>

                </div>
            </div>

        </main>

        <!-- ASIDE -->
        <div class="col-md-3 d-none d-md-block">
            <div class="p-3 border rounded">
                <h5>Menu rapide</h5>
                <ul class="list-unstyled">
                    <li><a href="FrontController?cmd=clientListe">Clients</a></li>
                    <li><a href="FrontController?cmd=prospectListe">Prospects</a></li>
                </ul>
            </div>
        </div>

    </div>
</div>

<!-- FOOTER -->
<footer class="text-center text-muted border-top mt-4 py-3">
    © 2026 CRM Formation AFPA – Cyril
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>