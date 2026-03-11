<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Liste des prospects</title>
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
<div class="container mt-4">
    <div class="row">

        <!-- CONTENU PRINCIPAL -->
        <main class="col-12 col-md-9">
            <h1 class="mb-4">Liste des prospects</h1>

            <div class="d-flex justify-content-end mb-3">
                <a class="btn btn-success" href="FrontController?cmd=prospectForm">Ajouter un prospect</a>
            </div>

            <div class="table-responsive">
                <table class="table table-striped align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>Nom</th>
                        <th>Ville</th>
                        <th>Téléphone</th>
                        <th class="text-end">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Bernard</td><td>Marseille</td><td>06 22 33 44 55</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Lefèvre</td><td>Bordeaux</td><td>07 55 66 77 88</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Rousseau</td><td>Lille</td><td>06 33 44 55 66</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Girard</td><td>Nice</td><td>07 44 55 66 77</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Fontaine</td><td>Rennes</td><td>06 55 66 77 88</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Chevalier</td><td>Montpellier</td><td>07 66 77 88 99</td>
                        <td class="text-end">
                            <a href="FrontController?cmd=prospectView" class="btn btn-primary btn-sm">Voir</a>
                            <a href="FrontController?cmd=prospectForm" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="FrontController?cmd=prospectSuppression" class="btn btn-danger btn-sm">Supprimer</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
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