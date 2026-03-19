<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>CRM – Formulaire Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Créer / Modifier un client</h1>

            <form class="row g-3" method="post" action="FrontController?cmd=clientForm">

                <!-- ID CLIENT (caché si modification) -->
                <c:if test="${not empty client.idClient}">
                    <input type="hidden" name="idClient" value="${client.idClient}">
                </c:if>

                <!-- ========================= -->
                <!-- RAISON SOCIALE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Raison sociale *</label>
                    <input type="text" name="raisonSociale" class="form-control"
                           value="${client.raisonSociale}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'raisonSociale'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- TELEPHONE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Téléphone *</label>
                    <input type="text" name="telephone" class="form-control"
                           value="${client.telephone}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'telephone'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE MAIL -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Adresse mail *</label>
                    <input type="email" name="adresseMail" class="form-control"
                           value="${client.adresseMail}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresseMail'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- CHIFFRE D'AFFAIRES -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" name="chiffreAffaires" class="form-control"
                           value="${client.chiffreAffaires}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'chiffreAffaires'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- NOMBRE EMPLOYES -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Nombre d'employés</label>
                    <input type="number" name="nombreEmployes" class="form-control"
                           value="${client.nombreEmployes}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'nombreEmployes'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- COMMENTAIRES -->
                <!-- ========================= -->
                <div class="col-12">
                    <label class="form-label">Commentaires</label>
                    <textarea name="commentaires" class="form-control" rows="3">${client.commentaires}</textarea>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE -->
                <!-- ========================= -->
                <h3 class="mt-4">Adresse</h3>

                <!-- ID ADRESSE (si modification) -->
                <c:if test="${not empty client.adresse.idAdresse}">
                    <input type="hidden" name="idAdresse" value="${client.adresse.idAdresse}">
                </c:if>

                <div class="col-md-4">
                    <label class="form-label">Numéro de rue *</label>
                    <input type="text" name="numeroRue" class="form-control"
                           value="${client.adresse.numeroRue}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.numeroRue'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-8">
                    <label class="form-label">Nom de rue *</label>
                    <input type="text" name="nomRue" class="form-control"
                           value="${client.adresse.nomRue}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.nomRue'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Code postal *</label>
                    <input type="text" name="codePostal" class="form-control"
                           value="${client.adresse.codePostal}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.codePostal'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-8">
                    <label class="form-label">Ville *</label>
                    <input type="text" name="ville" class="form-control"
                           value="${client.adresse.ville}"/>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.ville'}">
                            <div class="text-danger small">${err.message}</div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ========================= -->
                <!-- BOUTONS -->
                <!-- ========================= -->
                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=clientListe" class="btn btn-secondary">Retour</a>
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

</body>
</html>
