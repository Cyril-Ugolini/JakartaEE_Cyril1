<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Formulaire prospect</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">
                <%= request.getAttribute("mode") != null && request.getAttribute("mode").equals("modifier")
                        ? "Modifier un prospect"
                        : "Ajouter un prospect" %>
            </h1>

            <form class="row g-3" method="post" action="FrontController" novalidate>
                <input type="hidden" name="cmd" value="prospectForm">

                <div class="col-md-6">
                    <label for="prospect-nom" class="form-label">Nom <span class="text-danger">*</span></label>
                    <input type="text" id="prospect-nom" name="nom" class="form-control"
                           value="<%= request.getAttribute("nom") != null ? request.getAttribute("nom") : "" %>"
                           required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le nom est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-prenom" class="form-label">Prénom <span class="text-danger">*</span></label>
                    <input type="text" id="prospect-prenom" name="prenom" class="form-control"
                           value="<%= request.getAttribute("prenom") != null ? request.getAttribute("prenom") : "" %>"
                           required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le prénom est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-adresse" class="form-label">Adresse <span class="text-danger">*</span></label>
                    <input type="text" id="prospect-adresse" name="adresse" class="form-control"
                           value="<%= request.getAttribute("adresse") != null ? request.getAttribute("adresse") : "" %>"
                           required>
                    <div class="invalid-feedback">L'adresse est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-ville" class="form-label">Ville <span class="text-danger">*</span></label>
                    <input type="text" id="prospect-ville" name="ville" class="form-control"
                           value="<%= request.getAttribute("ville") != null ? request.getAttribute("ville") : "" %>"
                           required>
                    <div class="invalid-feedback">La ville est obligatoire.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-cp" class="form-label">Code postal <span class="text-danger">*</span></label>
                    <input type="text" id="prospect-cp" name="codePostal" class="form-control"
                           value="<%= request.getAttribute("codePostal") != null ? request.getAttribute("codePostal") : "" %>"
                           required pattern="[0-9]{5}">
                    <div class="invalid-feedback">Le code postal doit contenir 5 chiffres.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-tel" class="form-label">Téléphone <span class="text-danger">*</span></label>
                    <input type="tel" id="prospect-tel" name="telephone" class="form-control"
                           value="<%= request.getAttribute("telephone") != null ? request.getAttribute("telephone") : "" %>"
                           required>
                    <div class="invalid-feedback">Format téléphone invalide.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-email" class="form-label">Email <span class="text-danger">*</span></label>
                    <input type="email" id="prospect-email" name="email" class="form-control"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                           required>
                    <div class="invalid-feedback">Adresse email invalide.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-nb-employes" class="form-label">Nombre d'employés</label>
                    <input type="number" id="prospect-nb-employes" name="nbEmployes" class="form-control"
                           value="<%= request.getAttribute("nbEmployes") != null ? request.getAttribute("nbEmployes") : "" %>"
                           min="0">
                    <div class="invalid-feedback">Le nombre d'employés ne peut pas être négatif.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-ca" class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" id="prospect-ca" name="chiffreAffaires" class="form-control"
                           value="<%= request.getAttribute("chiffreAffaires") != null ? request.getAttribute("chiffreAffaires") : "" %>"
                           min="0">
                    <div class="invalid-feedback">Le CA ne peut pas être négatif.</div>
                </div>

                <div class="col-md-6">
                    <label for="prospect-interesse" class="form-label">Intérêt <span class="text-danger">*</span></label>
                    <select id="prospect-interesse" name="interesse" class="form-select" required>
                        <option value="">-- Sélectionner --</option>
                        <option value="OUI">Oui</option>
                        <option value="NON">Non</option>
                    </select>
                    <div class="invalid-feedback">Veuillez indiquer l'intérêt du prospect.</div>
                </div>

                <p class="text-muted small mt-2"><span class="text-danger">*</span> Champs obligatoires</p>

                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=prospectListe" class="btn btn-secondary">Retour</a>
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </div>

            </form>

        </main>

        <div id="tpl-aside" class="col-md-3 d-none d-md-block"></div>

    </div>
</div>

<div id="tpl-footer"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>

</body>
</html>