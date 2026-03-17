<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Prospect" %>
<%@ page import="models.Adresse" %>

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

<%
    List<Prospect> prospects = (List<Prospect>) request.getAttribute("prospects");
%>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Liste des prospects</h1>

            <div class="d-flex justify-content-end mb-3">
                <a href="FrontController?cmd=prospectForm" class="btn btn-success">
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
                    <% if (prospects != null && !prospects.isEmpty()) {
                        for (Prospect p : prospects) {
                            Adresse adr = p.getAdresse();
                    %>

                    <tr>
                        <td><%= p.getRaisonSociale() %></td>
                        <td><%= adr != null ? adr.getVille() : "" %></td>
                        <td><%= p.getTelephone() %></td>

                        <td class="text-center">

                            <a href="FrontController?cmd=prospectForm&mode=voir&id=<%= p.getIdProspect() %>"
                               class="btn btn-sm btn-primary me-1">Voir</a>

                            <a href="FrontController?cmd=prospectForm&mode=modifier&id=<%= p.getIdProspect() %>"
                               class="btn btn-sm btn-warning me-1">Modifier</a>

                            <a href="FrontController?cmd=prospectSuppression&id=<%= p.getIdProspect() %>"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Supprimer ce prospect ?');">
                                Supprimer
                            </a>

                        </td>
                    </tr>

                    <%  }
                    } else { %>

                    <tr>
                        <td colspan="4" class="text-center text-muted">
                            Aucun prospect enregistré.
                        </td>
                    </tr>

                    <% } %>
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
