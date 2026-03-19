<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>CRM – Liste des clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Liste des clients</h1>

            <div class="d-flex justify-content-end mb-3">
                <a href="FrontController?cmd=clientForm" class="btn btn-success">
                    Ajouter un client
                </a>
            </div>

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

                    <!-- Si la liste n'est pas vide -->
                    <c:if test="${not empty clients}">
                        <c:forEach var="c" items="${clients}">
                            <tr>
                                <td><c:out value="${c.raisonSociale}"/></td>
                                <td><c:out value="${c.adresse.ville}"/></td>
                                <td><c:out value="${c.telephone}"/></td>

                                <td class="text-center">

                                    <!-- Voir -->
                                    <a href="FrontController?cmd=clientForm&mode=voir&idClient=${c.idClient}"
                                       class="btn btn-sm btn-primary me-1">
                                        Voir
                                    </a>

                                    <!-- Modifier -->
                                    <a href="FrontController?cmd=clientForm&mode=modifier&idClient=${c.idClient}"
                                       class="btn btn-sm btn-warning me-1">
                                        Modifier
                                    </a>

                                    <!-- Supprimer -->
                                    <a href="FrontController?cmd=clientSuppression&id=${c.idClient}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Supprimer ce client ?');">
                                        Supprimer
                                    </a>

                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <!-- Si la liste est vide -->
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

        <div id="tpl-aside" class="col-md-3 d-none d-md-block"></div>

    </div>
</div>

<div id="tpl-footer"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
