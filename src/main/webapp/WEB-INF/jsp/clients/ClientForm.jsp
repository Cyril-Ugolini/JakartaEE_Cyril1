<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Set" %>
<%@ page import="jakarta.validation.ConstraintViolation" %>
<%@ page import="models.Client" %>
<%@ page import="models.Adresse" %>

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

<%
    Client client = (Client) request.getAttribute("client");
    Set<ConstraintViolation<Client>> errors =
            (Set<ConstraintViolation<Client>>) request.getAttribute("errors");

    if (client == null) {
        client = new Client();
        client.setAdresse(new Adresse());
    }
%>

<div class="container-fluid">
    <div class="row justify-content-center">

        <main class="col-12 col-md-7 p-4">

            <h1 class="mb-4">Créer / Modifier un client</h1>

            <form class="row g-3" method="post" action="FrontController?cmd=clientForm">

                <!-- ========================= -->
                <!-- RAISON SOCIALE -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Raison sociale *</label>
                    <input type="text" name="raisonSociale" class="form-control"
                           value="<%= client.getRaisonSociale() != null ? client.getRaisonSociale() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
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
                           value="<%= client.getTelephone() != null ? client.getTelephone() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
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
                           value="<%= client.getAdresseMail() != null ? client.getAdresseMail() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("adresseMail".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- CHIFFRE D'AFFAIRES -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Chiffre d'affaires (€)</label>
                    <input type="number" name="chiffreAffaires" class="form-control"
                           value="<%= client.getChiffreAffaires() %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("chiffreAffaires".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- NOMBRE EMPLOYES -->
                <!-- ========================= -->
                <div class="col-md-6">
                    <label class="form-label">Nombre d'employés</label>
                    <input type="number" name="nombreEmployes" class="form-control"
                           value="<%= client.getNombreEmployes() %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("nombreEmployes".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- COMMENTAIRES -->
                <!-- ========================= -->
                <div class="col-12">
                    <label class="form-label">Commentaires</label>
                    <textarea name="commentaires" class="form-control" rows="3"><%= client.getCommentaires() != null ? client.getCommentaires() : "" %></textarea>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE : NUMERO RUE -->
                <!-- ========================= -->
                <h3 class="mt-4">Adresse</h3>

                <div class="col-md-4">
                    <label class="form-label">Numéro de rue *</label>
                    <input type="text" name="numeroRue" class="form-control"
                           value="<%= client.getAdresse().getNumeroRue() != null ? client.getAdresse().getNumeroRue() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("adresse.numeroRue".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- ADRESSE : NOM RUE -->
                <!-- ========================= -->
                <div class="col-md-8">
                    <label class="form-label">Nom de rue *</label>
                    <input type="text" name="nomRue" class="form-control"
                           value="<%= client.getAdresse().getNomRue() != null ? client.getAdresse().getNomRue() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("adresse.nomRue".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- CODE POSTAL -->
                <!-- ========================= -->
                <div class="col-md-4">
                    <label class="form-label">Code postal *</label>
                    <input type="text" name="codePostal" class="form-control"
                           value="<%= client.getAdresse().getCodePostal() != null ? client.getAdresse().getCodePostal() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
                            if ("adresse.codePostal".equals(err.getPropertyPath().toString())) { %>
                    <div class="text-danger small"><%= err.getMessage() %></div>
                    <%      }
                    }
                    } %>
                </div>

                <!-- ========================= -->
                <!-- VILLE -->
                <!-- ========================= -->
                <div class="col-md-8">
                    <label class="form-label">Ville *</label>
                    <input type="text" name="ville" class="form-control"
                           value="<%= client.getAdresse().getVille() != null ? client.getAdresse().getVille() : "" %>">

                    <% if (errors != null) {
                        for (ConstraintViolation<Client> err : errors) {
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
