<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Ajouter / Modifier un client</title>
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
        <main class="col-12 col-md-9" role="main">
            <h1 class="mb-4">Ajouter un client</h1>

            <form class="row g-3" method="post" action="FrontController" novalidate>
                <input type="hidden" name="cmd" value="clientForm">

                <div class="col-md-6">
                    <label for="client-nom" class="form-label">Nom <span class="text-danger">*</span></label>
                    <input type="text" id="client-nom" name="nom" class="form-control" placeholder="Nom du client" required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le nom est obligatoire (2 caractères minimum).</div>
                </div>

                <div class="col-md-6">
                    <label for="client-prenom" class="form-label">Prénom <span class="text-danger">*</span></label>
                    <input type="text" id="client-prenom" name="prenom" class="form-control" placeholder="Prénom du client" required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le prénom est obligatoire (2 caractères minimum).</div>
                </div>

                <div class="col-md-6">
                    <label for="client-adresse" class="form-label">Adresse <span class="text-danger">*</span></label>
                    <input type="text" id="client-adresse" name="adresse" class="form-control" placeholder="Adresse" required>
                    <div class="invalid-feedback">L'adresse est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="client-ville" class="form-label">Ville <span class="text-danger">*</span></label>
                    <input type="text" id="client-ville" name="ville" class="form-control" placeholder="Ville" required>
                    <div class="invalid-feedback">La ville est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="client-cp" class="form-label">Code postal <span class="text-danger">*</span></label>
                    <input type="text" id="client-cp" name="codePostal" class="form-control" placeholder="Ex : 75001" required pattern="[0-9]{5}">
                    <div class="invalid-feedback">Le code postal doit contenir 5 chiffres.</div>
                </div>

                <div class="col-md-6">
                    <label for="client-tel" class="form-label">Téléphone <span class="text-danger">*</span></label>
                    <input type="tel" id="client-tel" name="telephone" class="form-control" placeholder="Ex : 06 00 00 00 00" required>
                    <div class="invalid-feedback">Format téléphone invalide (ex : 06 00 00 00 00).</div>
                </div>

                <div class="col-md-6">
                    <label for="client-email" class="form-label">Email <span class="text-danger">*</span></label>
                    <input type="email" id="client-email" name="email" class="form-control" placeholder="Adresse email" required>
                    <div class="invalid-feedback">Veuillez saisir une adresse email valide.</div>
                </div>

                <div class="col-md-6">
                    <label for="client-ca" class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" id="client-ca" name="chiffreAffaires" class="form-control" placeholder="Ex : 50000" min="0">
                    <div class="invalid-feedback">Le chiffre d'affaires ne peut pas être négatif.</div>
                </div>

                <p class="text-muted small mt-2"><span class="text-danger">*</span> Champs obligatoires</p>

                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=clientListe" class="btn btn-secondary">Retour</a>
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </div>

            </form>
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