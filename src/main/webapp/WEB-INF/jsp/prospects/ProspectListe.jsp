<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>CRM – Liste des prospects</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Liste des prospects</h1>

            <div class="d-flex justify-content-end mb-3">
                <a href="FrontController?cmd=prospectForm"
                   class="btn btn-success">
                    Ajouter un prospect
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

                    <c:if test="${not empty prospects}">
                        <c:forEach var="p" items="${prospects}">
                            <tr>
                                <td>
                                    <c:out value="${p.raisonSociale}"/>
                                </td>
                                <td>
                                    <c:out value="${p.adresse.ville}"/>
                                </td>
                                <td>
                                    <c:out value="${p.telephone}"/>
                                </td>
                                <td class="text-end">
                                    <div class="d-flex flex-column gap-1 align-items-end">
                                        <a href="FrontController?cmd=prospectView&id=${p.idProspect}"
                                           class="btn btn-primary btn-sm w-100">
                                            Voir
                                        </a>
                                        <div class="d-flex gap-1 w-100">
                                            <a href="FrontController?cmd=prospectForm&mode=modifier&id=${p.idProspect}"
                                               class="btn btn-warning btn-sm flex-fill">
                                                Modifier
                                            </a>
                                            <a href="FrontController?cmd=prospectSuppression&id=${p.idProspect}"
                                               class="btn btn-danger btn-sm flex-fill"
                                               onclick="return confirm('Supprimer ce prospect ?');">
                                                Supprimer
                                            </a>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty prospects}">
                        <tr>
                            <td colspan="4" class="text-center text-muted">
                                Aucun prospect enregistré.
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
