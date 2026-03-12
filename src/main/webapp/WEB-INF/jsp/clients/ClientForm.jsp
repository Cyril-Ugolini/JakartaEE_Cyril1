<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Formulaire client</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Ton CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<!-- HEADER dynamique -->
<header id="header"></header>

<div class="container-fluid">
    <div class="row">

        <!-- ASIDE dynamique -->
        <aside id="aside" class="col-md-3 d-none d-md-block"></aside>

        <!-- CONTENU PRINCIPAL -->
        <main class="col-12 col-md-9 p-4">

            <%
                String mode = request.getParameter("mode");
                boolean isEdit = "modifier".equals(mode);
            %>

            <h1 class="mb-4">
                <%= isEdit ? "Modifier un client" : "Créer un client" %>
            </h1>

            <!-- FORMULAIRE CLIENT -->
            <form class="row g-3" method="post" action="FrontController?cmd=clientForm" novalidate>

                <!-- ID caché si modification -->
                <input type="hidden" name="id"
                       value="<%= request.getAttribute("id") != null ? request.getAttribute("id") : "" %>">

                <!-- NOM -->
                <div class="col-md-6">
                    <label for="client-nom" class="form-label">Nom *</label>
                    <input type="text" id="client-nom" name="nom" class="form-control"
                           value="<%= request.getAttribute("nom") != null ? request.getAttribute("nom") : "" %>"
                           required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le nom est obligatoire.</div>
                </div>

                <!-- PRENOM -->
                <div class="col-md-6">
                    <label for="client-prenom" class="form-label">Prénom *</label>
                    <input type="text" id="client-prenom" name="prenom" class="form-control"
                           value="<%= request.getAttribute("prenom") != null ? request.getAttribute("prenom") : "" %>"
                           required minlength="2" maxlength="100">
                    <div class="invalid-feedback">Le prénom est obligatoire.</div>
                </div>

                <!-- ADRESSE -->
                <div class="col-md-6">
                    <label for="client-adresse" class="form-label">Adresse *</label>
                    <input type="text" id="client-adresse" name="adresse" class="form-control"
                           value="<%= request.getAttribute("adresse") != null ? request.getAttribute("adresse") : "" %>"
                           required>
                    <div class="invalid-feedback">L'adresse est obligatoire.</div>
                </div>

                <!-- VILLE -->
                <div class="col-md-6">
                    <label for="client-ville" class="form-label">Ville *</label>
                    <input type="text" id="client-ville" name="ville" class="form-control"
                           value="<%= request.getAttribute("ville") != null ? request.getAttribute("ville") : "" %>"
                           required>
                    <div class="invalid-feedback">La ville est obligatoire.</div>
                </div>

                <!-- CODE POSTAL -->
                <div class="col-md-6">
                    <label for="client-cp" class="form-label">Code postal *</label>
                    <input type="text" id="client-cp" name="codePostal" class="form-control"
                           value="<%= request.getAttribute("codePostal") != null ? request.getAttribute("codePostal") : "" %>"
                           required pattern="[0-9]{5}">
                    <div class="invalid-feedback">Le code postal doit contenir 5 chiffres.</div>
                </div>

                <!-- TELEPHONE -->
                <div class="col-md-6">
                    <label for="client-tel" class="form-label">Téléphone *</label>
                    <input type="tel" id="client-tel" name="telephone" class="form-control"
                           value="<%= request.getAttribute("telephone") != null ? request.getAttribute("telephone") : "" %>"
                           required>
                    <div class="invalid-feedback">Format téléphone invalide.</div>
                </div>

                <!-- EMAIL -->
                <div class="col-md-6">
                    <label for="client-email" class="form-label">Email *</label>
                    <input type="email" id="client-email" name="email" class="form-control"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                           required>
                    <div class="invalid-feedback">Adresse email invalide.</div>
                </div>

                <!-- CHIFFRE D'AFFAIRES -->
                <div class="col-md-6">
                    <label for="client-ca" class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" id="client-ca" name="chiffreAffaires" class="form-control"
                           value="<%= request.getAttribute("chiffreAffaires") != null ? request.getAttribute("chiffreAffaires") : "" %>"
                           min="0">
                    <div class="invalid-feedback">Le CA ne peut pas être négatif.</div>
                </div>

                <p class="text-muted small mt-2"><span class="text-danger">*</span> Champs obligatoires</p>

                <!-- BOUTONS -->
                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=clientListe" class="btn btn-secondary">Retour</a>
                    <button type="submit" class="btn btn-success">
                        <%= isEdit ? "Enregistrer les modifications" : "Créer le client" %>
                    </button>
                </div>

            </form>

        </main>
    </div>
</div>

<!-- FOOTER dynamique -->
<footer id="footer"></footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- URL du template -->
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>

</body>
</html>