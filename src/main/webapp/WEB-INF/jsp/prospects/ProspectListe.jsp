<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Liste des prospects</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="full">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

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