<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Suppression client</title>

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

            <h1 class="mb-4">Supprimer un client</h1>

            <div class="card shadow-sm">
                <div class="card-body">

                    <h4 class="card-title mb-3">Voulez-vous vraiment supprimer ce client ?</h4>

                    <p><strong>Nom :</strong> <%= request.getAttribute("nom") %></p>
                    <p><strong>Ville :</strong> <%= request.getAttribute("ville") %></p>
                    <p><strong>Téléphone :</strong> <%= request.getAttribute("telephone") %></p>

                    <div class="alert alert-danger mt-4">
                        Cette action est <strong>définitive</strong> et ne peut pas être annulée.
                    </div>

                    <div class="d-flex justify-content-between mt-4">

                        <a href="FrontController?cmd=clientListe" class="btn btn-secondary">
                            Annuler
                        </a>

                        <form method="post" action="FrontController?cmd=clientSuppression" class="d-inline">
                            <input type="hidden" name="id"
                                   value="<%= request.getAttribute("id") %>">
                            <button type="submit" class="btn btn-danger">
                                Supprimer définitivement
                            </button>
                        </form>

                    </div>

                </div>
            </div>

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