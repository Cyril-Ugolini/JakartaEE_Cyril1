<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRM - Politique de confidentialité</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body data-header="retour">

<div id="tpl-header"></div>

<div class="container py-5">
    <main>
        <h1 class="mb-4">Politique de confidentialité</h1>
        <p class="text-muted">Conformément au Règlement Général sur la Protection des Données (RGPD – UE 2016/679).</p>

        <section class="mb-4">
            <h2 class="h5">1. Responsable du traitement</h2>
            <p>
                Dans le cadre de ce projet pédagogique, le responsable du traitement est l'étudiant auteur du projet (Cyril), supervisé par l'AFPA.<br>
                Contact : via le formateur AFPA.
            </p>
        </section>

        <section class="mb-4">
            <h2 class="h5">2. Données collectées</h2>
            <p>Dans le cadre de ce CRM, les données suivantes peuvent être saisies :</p>
            <ul>
                <li>Nom et prénom</li>
                <li>Adresse postale (rue, ville, code postal)</li>
                <li>Adresse email</li>
                <li>Numéro de téléphone</li>
                <li>Chiffre d'affaires et nombre d'employés (prospects)</li>
            </ul>
            <p>Ces données sont <strong>fictives</strong> dans ce contexte de démonstration.</p>
        </section>

        <section class="mb-4">
            <h2 class="h5">3. Finalité du traitement</h2>
            <p>Les données sont utilisées uniquement à des fins de démonstration pédagogique,
                dans le cadre de l'ECF (Évaluation en Cours de Formation) AFPA. Elles ne sont pas transmises à des tiers.</p>
        </section>

        <section class="mb-4">
            <h2 class="h5">4. Durée de conservation</h2>
            <p>Les données ne sont pas conservées au-delà de la durée de la session. Aucun stockage persistant n'est mis en place dans cette version front-end.</p>
        </section>

        <section class="mb-4" id="droits">
            <h2 class="h5">5. Vos droits (RGPD)</h2>
            <p>Conformément au RGPD, vous disposez des droits suivants sur vos données personnelles :</p>
            <ul>
                <li><strong>Droit d'accès</strong> : vous pouvez demander à consulter les données vous concernant.</li>
                <li><strong>Droit de rectification</strong> : vous pouvez demander la correction de données inexactes.</li>
                <li><strong>Droit à l'effacement</strong> : vous pouvez demander la suppression de vos données (« droit à l'oubli »).</li>
                <li><strong>Droit à la limitation du traitement</strong> : vous pouvez demander la suspension du traitement.</li>
                <li><strong>Droit à la portabilité</strong> : vous pouvez recevoir vos données dans un format structuré.</li>
                <li><strong>Droit d'opposition</strong> : vous pouvez vous opposer au traitement de vos données.</li>
            </ul>
            <p>Pour exercer ces droits, contactez le responsable de traitement via votre formateur AFPA.</p>
            <p>En cas de litige, vous pouvez saisir la <strong>CNIL</strong> :
                <a href="https://www.cnil.fr" target="_blank" rel="noopener noreferrer">www.cnil.fr</a>.
            </p>
        </section>

        <section class="mb-4">
            <h2 class="h5">6. Cookies</h2>
            <p>Ce site n'utilise pas de cookies de traçage. Seuls les éventuels cookies techniques nécessaires au bon fonctionnement de l'application peuvent être utilisés.</p>
        </section>

        <a href="${pageContext.request.contextPath}/FrontController?cmd=accueil" class="btn btn-secondary mt-3">
            Retour à l'accueil
        </a>
    </main>
</div>

<div id="tpl-footer"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const TEMPLATE_URL = "${pageContext.request.contextPath}/FrontController?cmd=template";
</script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>

</body>
</html>
