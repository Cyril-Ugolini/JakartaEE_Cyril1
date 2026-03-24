<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!-- ============================================================================
FICHIER : ClientListe.jsp
ROLE : Affiche la liste des clients enregistrés dans le CRM.
ARCHITECTURE : MVC (Jakarta EE)
============================================================================ -->

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>CRM – Liste des clients</title>

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

            <h1 class="mb-4">Liste des clients</h1>

            <!-- Bouton d'ajout -->
            <div class="d-flex justify-content-end mb-3">
                <a href="FrontController?cmd=clientForm" class="btn btn-success">
                    Ajouter un client
                </a>
            </div>

            <!-- Tableau des clients -->
            <div class="table-responsive">
                <table class="table table-dark table-striped align-middle">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Ville</th>
                        <th>Téléphone</th>
                        <th class="text-center">Actions</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:if test="${not empty clients}">
                        <c:forEach var="c" items="${clients}">
                            <tr>
                                <td><c:out value="${c.raisonSociale}" /></td>
                                <td><c:out value="${c.adresse.ville}" /></td>
                                <td><c:out value="${c.telephone}" /></td>

                                <td class="text-end">
                                    <div class="d-flex flex-column gap-1 align-items-end">

                                        <!-- Voir -->
                                        <a href="FrontController?cmd=clientView&idClient=${c.idClient}"
                                           class="btn btn-primary btn-sm w-100">
                                            Voir
                                        </a>

                                        <div class="d-flex gap-1 w-100">

                                            <!-- Modifier -->
                                            <a href="FrontController?cmd=clientForm&mode=modifier&idClient=${c.idClient}"
                                               class="btn btn-warning btn-sm flex-fill">
                                                Modifier
                                            </a>

                                            <!-- Supprimer (POST + CSRF) -->
                                            <form method="post" action="FrontController?cmd=clientSuppression" class="flex-fill">
                                                <input type="hidden" name="idClient" value="${c.idClient}">
                                                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                                <button type="submit"
                                                        class="btn btn-danger btn-sm w-100"
                                                        onclick="return confirm('Supprimer ce client ?');">
                                                    Supprimer
                                                </button>
                                            </form>

                                        </div>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty clients}">
                        <tr>
                            <td colspan="4" class="text-center text-muted">
                                Aucun client enregistré.
                            </td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>
            </div>

        </main>

        <div id="tpl-aside" class="col-md-3"></div>

    </div>
</div>

<div id="tpl-footer"></div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
