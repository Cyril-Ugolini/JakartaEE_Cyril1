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

            <h1 class="mb-4">
                <c:out value="Créer / Modifier un client"/>
            </h1>

            <!-- FORMULAIRE -->
            <form id="client-form"
                  class="row g-3"
                  method="post"
                  action="FrontController?cmd=clientForm"
                  novalidate>

                <!-- TOKEN CSRF -->
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>

                <!-- ID CLIENT -->
                <c:if test="${not empty client.idClient}">
                    <input type="hidden" name="idClient" value="<c:out value='${client.idClient}'/>"/>
                </c:if>

                <!-- RAISON SOCIALE -->
                <div class="col-md-6">
                    <label class="form-label">Raison sociale *</label>
                    <input type="text" name="raisonSociale" class="form-control"
                           value="<c:out value='${client.raisonSociale}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'raisonSociale'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- TELEPHONE -->
                <div class="col-md-6">
                    <label class="form-label">Téléphone *</label>
                    <input type="text" name="telephone" class="form-control"
                           value="<c:out value='${client.telephone}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'telephone'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- ADRESSE MAIL -->
                <div class="col-md-6">
                    <label class="form-label">Adresse mail *</label>
                    <input type="email" name="adresseMail" class="form-control"
                           value="<c:out value='${client.adresseMail}'/>" required>

                    <div class="invalid-feedback">Adresse mail invalide.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresseMail'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- CHIFFRE D'AFFAIRES -->
                <div class="col-md-6">
                    <label class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" name="chiffreAffaires" class="form-control"
                           value="<c:out value='${client.chiffreAffaires}'/>">
                </div>

                <!-- NOMBRE EMPLOYÉS -->
                <div class="col-md-6">
                    <label class="form-label">Nombre d'employés</label>
                    <input type="number" name="nombreEmployes" class="form-control"
                           value="<c:out value='${client.nombreEmployes}'/>">
                </div>

                <!-- COMMENTAIRES -->
                <div class="col-12">
                    <label class="form-label">Commentaires</label>
                    <textarea name="commentaires" class="form-control" rows="3"><c:out value="${client.commentaires}"/></textarea>
                </div>

                <!-- ADRESSE -->
                <h3 class="mt-4"><c:out value="Adresse"/></h3>

                <!-- ID ADRESSE -->
                <c:if test="${not empty client.adresse.idAdresse}">
                    <input type="hidden" name="idAdresse" value="<c:out value='${client.adresse.idAdresse}'/>"/>
                </c:if>

                <!-- NUMERO RUE -->
                <div class="col-md-4">
                    <label class="form-label">Numéro de rue *</label>
                    <input type="text" name="numeroRue" class="form-control"
                           value="<c:out value='${client.adresse.numeroRue}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.numeroRue'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- NOM RUE -->
                <div class="col-md-8">
                    <label class="form-label">Nom de rue *</label>
                    <input type="text" name="nomRue" class="form-control"
                           value="<c:out value='${client.adresse.nomRue}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.nomRue'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- CODE POSTAL -->
                <div class="col-md-4">
                    <label class="form-label">Code postal *</label>
                    <input type="text" name="codePostal" class="form-control"
                           value="<c:out value='${client.adresse.codePostal}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.codePostal'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- VILLE -->
                <div class="col-md-8">
                    <label class="form-label">Ville *</label>
                    <input type="text" name="ville" class="form-control"
                           value="<c:out value='${client.adresse.ville}'/>" required>

                    <div class="invalid-feedback">Champ obligatoire.</div>

                    <c:forEach var="err" items="${errors}">
                        <c:if test="${err.propertyPath == 'adresse.ville'}">
                            <div class="text-danger small"><c:out value="${err.message}"/></div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- BOUTONS -->
                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=clientListe" class="btn btn-secondary">
                        <c:out value="Retour"/>
                    </a>
                    <button type="submit" class="btn btn-success">
                        <c:out value="Enregistrer"/>
                    </button>
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

<!-- VALIDATION BOOTSTRAP -->
<script>
    document.addEventListener("DOMContentLoaded", function () {

        const form = document.getElementById("client-form");

        form.addEventListener("submit", function (event) {

            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add("was-validated");
        });

    });
</script>

</body>
</html>
