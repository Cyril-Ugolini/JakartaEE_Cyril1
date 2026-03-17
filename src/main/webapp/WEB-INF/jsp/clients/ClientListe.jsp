<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM – Liste des clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="full">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Liste des clients</h1>

            <div class="d-flex justify-content-end mb-3">
                <a class="btn btn-success" href="FrontController?cmd=clientForm">Ajouter un client</a>
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
                        <td>Durand</td><td>Paris</td><td>06 00 00 00 00</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Martin</td><td>Lyon</td><td>07 11 22 33 44</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Petit</td><td>Toulouse</td><td>06 33 44 55 66</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Moreau</td><td>Nantes</td><td>07 44 55 66 77</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Simon</td><td>Strasbourg</td><td>06 55 66 77 88</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Laurent</td><td>Bordeaux</td><td>07 66 77 88 99</td>
                        <td class="text-end">
                            <div class="d-flex flex-column gap-1 align-items-end">
                                <a href="FrontController?cmd=clientView" class="btn btn-primary btn-sm w-100">Voir</a>
                                <div class="d-flex gap-1 w-100">
                                    <a href="FrontController?cmd=clientForm" class="btn btn-warning btn-sm flex-fill">Modifier</a>
                                    <a href="FrontController?cmd=clientSuppression" class="btn btn-danger btn-sm flex-fill">Supprimer</a>
                                </div>
                            </div>
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