<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Set" %>
<%@ page import="jakarta.validation.ConstraintViolation" %>
<%@ page import="models.Prospect" %>
<%@ page import="models.Adresse" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>CRM – Formulaire prospect</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<%
    Prospect prospect = (Prospect) request.getAttribute("prospect");
    Set<ConstraintViolation<Prospect>> errors =
            (Set<ConstraintViolation<Prospect>>) request.getAttribute("errors");

    if (prospect == null) {
        prospect = new Prospect();
        prospect.setAdresse(new Adresse());
    }
%>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">
                <%= request.getAttribute("mode") != null && request.getAttribute("mode").equals("modifier")
                        ? "Modifier un prospect"
                        : "Ajouter un prospect" %>
            </h1>

            <form class="row g-3" method="post" action="FrontController?cmd=prospectForm">

                <!-- ========================= -->
                <!-- RAISON SOCIALE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Raison sociale *</label>
                    <input type="text" name="raisonSociale" class="form-control"
                           value="<%= prospect.getRaisonSociale() != null ? prospect.getRaisonSociale() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("raisonSociale".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- TELEPHONE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Téléphone *</label>
                    <input type="text" name="telephone" class="form-control"
                           value="<%= prospect.getTelephone() != null ? prospect.getTelephone() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("telephone".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE MAIL -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Adresse mail *</label>
                    <input type="email" name="adresseMail" class="form-control"
                           value="<%= prospect.getAdresseMail() != null ? prospect.getAdresseMail() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("adresseMail".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- DATE PROSPECTION -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Date de prospection *</label>
                    <input type="date" name="dateProspection" class="form-control"
                           value="<%= prospect.getDateProspection() != null ? prospect.getDateProspection() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("dateProspection".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- INTERESSE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Intérêt *</label>
                    <select name="interesse" class="form-select">
                        <option value="">-- Sélectionner --</option>
                        <option value="OUI" <%= "OUI".equals(String.valueOf(prospect.getInteresse())) ? "selected" : "" %>>Oui</option>
                        <option value="NON" <%= "NON".equals(String.valueOf(prospect.getInteresse())) ? "selected" : "" %>>Non</option>
                    </select>

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("interesse".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE -->
                <!-- ========================= -->
                <h3 class="mt-4">Adresse</h3>

                <div class="col-md-4">
                    <label class="form-label">Numéro de rue *</label>
                    <input type="text" name="numeroRue" class="form-control"
                           value="<%= prospect.getAdresse().getNumeroRue() != null ? prospect.getAdresse().getNumeroRue() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("adresse.numeroRue".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <div class="col-md-8">
                    <label class="form-label">Nom de rue *</label>
                    <input type="text" name="nomRue" class="form-control"
                           value="<%= prospect.getAdresse().getNomRue() != null ? prospect.getAdresse().getNomRue() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("adresse.nomRue".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Code postal *</label>
                    <input type="text" name="codePostal" class="form-control"
                           value="<%= prospect.getAdresse().getCodePostal() != null ? prospect.getAdresse().getCodePostal() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("adresse.codePostal".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <div class="col-md-8">
                    <label class="form-label">Ville *</label>
                    <input type="text" name="ville" class="form-control"
                           value="<%= prospect.getAdresse().getVille() != null ? prospect.getAdresse().getVille() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Prospect> err : errors) {
                            if ("adresse.ville".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- BOUTONS -->
                <!-- ========================= -->
                <div class="col-12 d-flex justify-content-between mt-4">
                    <a href="FrontController?cmd=prospectListe" class="btn btn-secondary">Retour</a>
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
