<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!-- ============================================================================
FICHIER : ClientSuppression.jsp
ROLE : Confirmation avant suppression d’un client
ARCHITECTURE : MVC (Jakarta EE)
============================================================================ -->

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Suppression client</title>

    <!-- Styles -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<!-- Header dynamique -->
<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">
                <c:out value="Supprimer un client"/>
            </h1>

            <div class="card shadow-sm">
                <div class="card-body">

                    <h4 class="card-title mb-3">
                        <c:out value="Voulez-vous vraiment supprimer ce client ?"/>
                    </h4>

                    <p>
                        <strong>Nom :</strong>
                        <c:out value="${client.raisonSociale}" />
                    </p>

                    <p>
                        <strong>Ville :</strong>
                        <c:out value="${client.adresse.ville}" />
                    </p>

                    <p>
                        <strong>Téléphone :</strong>
                        <c:out value="${client.telephone}" />
                    </p>

                    <div class="alert alert-danger mt-4">
                        <c:out value="Cette action est définitive et ne peut pas être annulée."/>
                    </div>

                    <div class="d-flex justify-content-between mt-4">

                        <!-- Annuler -->
                        <a href="FrontController?cmd=clientListe" class="btn btn-secondary">
                            <c:out value="Annuler"/>
                        </a>

                        <!-- Formulaire de suppression -->
                        <form method="post" action="FrontController?cmd=clientSuppression" class="d-inline">
                            <input type="hidden" name="idClient" value="${client.idClient}" />
                            <button type="submit" class="btn btn-danger">
                                <c:out value="Supprimer définitivement"/>
                            </button>
                        </form>

                    </div>

                </div>
            </div>

        </main>

        <div id="tpl-aside" class="col-md-3 d-none d-md-block"></div>

    </div>
</div>

<!-- Footer -->
<div id="tpl-footer"></div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
