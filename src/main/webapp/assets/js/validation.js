/**
 * validation.js
 * Gestion de la validation des formulaires et de l'affichage
 * de la carte Leaflet + météo Open-Meteo.
 *
 * APIs utilisées :
 *  - api-adresse.data.gouv.fr  → géolocalisation (adresse → lat/lng)
 *  - OpenStreetMap via Leaflet → affichage carte interactive
 *  - api.open-meteo.com        → météo en temps réel
 *
 * @author Cyril
 * @version 1.0
 */

document.addEventListener('DOMContentLoaded', function () {

    // ─── Constantes métier ───────────────────────────────────────────────────────

    /**
     * Limites métier pour la validation des formulaires.
     * @type {Object}
     */
    const METIER = {
        CA_MAX: 10_000_000_000,   // 10 milliards €
        EMPLOYES_MAX: 1_000_000,  // 1 million d'employés
        CA_MIN: 0,
        EMPLOYES_MIN: 0
    };

    // ─── Utilitaires ─────────────────────────────────────────────────────────────

    /**
     * Affiche un message d'erreur dans le formulaire.
     * @param {string} formId  - L'id du formulaire cible
     * @param {string} message - Le message d'erreur à afficher
     */
    function showFormError(formId, message) {
        const errorDiv = document.querySelector(`#${formId} #form-error`);
        if (!errorDiv) return;
        errorDiv.textContent = message;
        errorDiv.classList.remove('d-none');
    }

    /**
     * Cache le message d'erreur dans le formulaire.
     * @param {string} formId - L'id du formulaire cible
     */
    function hideFormError(formId) {
        const errorDiv = document.querySelector(`#${formId} #form-error`);
        if (!errorDiv) return;
        errorDiv.classList.add('d-none');
        errorDiv.textContent = '';
    }

    /**
     * Valide les règles métier du formulaire (CA, nb employés).
     * Complète la validation HTML5 native.
     * @param {string} formId - L'id du formulaire cible
     * @param {string} type   - 'client' ou 'prospect'
     * @returns {string[]}    - Tableau des messages d'erreur (vide si tout est valide)
     */
    function validateMetier(formId, type) {
        const errors = [];

        // Chiffre d'affaires
        const caInput = document.querySelector(`#${formId} [name="chiffreAffaires"]`);
        if (caInput && caInput.value !== '') {
            const ca = parseFloat(caInput.value);
            if (isNaN(ca) || ca < METIER.CA_MIN) {
                errors.push("Le chiffre d'affaires ne peut pas être négatif.");
                caInput.classList.add('is-invalid');
            } else if (ca > METIER.CA_MAX) {
                errors.push(`Le chiffre d'affaires semble trop élevé (maximum ${METIER.CA_MAX.toLocaleString('fr-FR')} €).`);
                caInput.classList.add('is-invalid');
            } else {
                caInput.classList.remove('is-invalid');
                caInput.classList.add('is-valid');
            }
        }

        // Nombre d'employés (prospects uniquement)
        if (type === 'prospect') {
            const nbInput = document.querySelector(`#${formId} [name="nbEmployes"]`);
            if (nbInput && nbInput.value !== '') {
                const nb = parseInt(nbInput.value, 10);
                if (isNaN(nb) || nb < METIER.EMPLOYES_MIN) {
                    errors.push("Le nombre d'employés ne peut pas être négatif.");
                    nbInput.classList.add('is-invalid');
                } else if (nb > METIER.EMPLOYES_MAX) {
                    errors.push(`Le nombre d'employés semble trop élevé (maximum ${METIER.EMPLOYES_MAX.toLocaleString('fr-FR')}).`);
                    nbInput.classList.add('is-invalid');
                } else {
                    nbInput.classList.remove('is-invalid');
                    nbInput.classList.add('is-valid');
                }
            }
        }

        return errors;
    }

    // ─── Initialisation formulaire client / prospect ──────────────────────────────

    /**
     * Initialise la validation d'un formulaire client ou prospect.
     * Effectue la validation HTML5 native puis la validation métier JS.
     * @param {string} formId - L'id du formulaire ('client-form' ou 'prospect-form')
     * @param {string} type   - 'client' ou 'prospect'
     */
    window.initFormValidation = function (formId, type) {
        const form = document.getElementById(formId);
        if (!form) return;

        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            hideFormError(formId);

            // Validation HTML5 native
            const html5Valid = form.checkValidity();
            form.classList.add('was-validated');

            // Validation métier JS
            const metierErrors = validateMetier(formId, type);

            if (!html5Valid) {
                showFormError(formId, 'Veuillez corriger les erreurs dans le formulaire avant de continuer.');
                return;
            }

            if (metierErrors.length > 0) {
                showFormError(formId, metierErrors.join(' '));
                return;
            }

            // Formulaire valide — ici on pourra appeler l'API serveur
            alert('Formulaire valide ! (envoi au serveur à connecter)');
            // TODO : remplacer par fetch() vers le backend JakartaEE
        });

        // Réinitialise les erreurs à chaque saisie
        form.querySelectorAll('input, select, textarea').forEach(input => {
            input.addEventListener('input', () => hideFormError(formId));
        });
    };

    // ─── Initialisation formulaire login ─────────────────────────────────────────

    /**
     * Initialise la validation du formulaire de connexion.
     * Vérifie que les champs identifiant et mot de passe sont renseignés.
     * TODO : remplacer l'alert par un fetch() vers le backend JakartaEE.
     */
    window.initLoginValidation = function () {
        const form = document.getElementById('login-form');
        if (!form) return;

        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            const errorDiv = document.getElementById('login-error');
            errorDiv.classList.add('d-none');
            errorDiv.textContent = '';

            const html5Valid = form.checkValidity();
            form.classList.add('was-validated');

            if (!html5Valid) {
                errorDiv.textContent = 'Veuillez renseigner votre identifiant et votre mot de passe.';
                errorDiv.classList.remove('d-none');
                return;
            }

            // Formulaire valide — ici on pourra appeler l'API d'authentification
            alert('Connexion valide ! (authentification serveur à connecter)');
            // TODO : remplacer par fetch() vers le backend JakartaEE
        });
    };

    // ─── Auto-initialisation selon la page ───────────────────────────────────────

    if (document.getElementById('client-form')) {
        window.initFormValidation('client-form', 'client');
    }

    if (document.getElementById('prospect-form')) {
        window.initFormValidation('prospect-form', 'prospect');
    }

    if (document.getElementById('login-form')) {
        window.initLoginValidation();
    }

    // ─── Carte Leaflet + Météo Open-Meteo ────────────────────────────────────────

    /**
     * Initialise la carte Leaflet et la météo pour une adresse donnée.
     *
     * Flux des appels :
     *  1. API Adresse (data.gouv.fr) → récupère lat/lng depuis l'adresse texte
     *  2. Leaflet + OpenStreetMap    → affiche la carte avec un marqueur
     *  3. Open-Meteo                 → récupère la météo actuelle (temp, vent, conditions)
     *
     * Les données sont lues depuis le localStorage :
     *  - clientAVoir   → fiche client
     *  - prospectAVoir → fiche prospect
     *
     * @param {string} adresse    - Rue du client/prospect
     * @param {string} codePostal - Code postal
     * @param {string} ville      - Ville
     * @param {string} nom        - Nom (affiché dans le popup de la carte)
     * @param {string} prenom     - Prénom (affiché dans le popup de la carte)
     */
    window.initCarteMeteo = function (adresse, codePostal, ville, nom, prenom) {

        const adresseComplete = adresse + ' ' + codePostal + ' ' + ville;

        // Étape 1 : API Adresse gouvernement → latitude/longitude
        fetch('https://api-adresse.data.gouv.fr/search/?q=' + encodeURIComponent(adresseComplete) + '&limit=1')
            .then(res => res.json())
            .then(data => {
                if (data.features && data.features.length > 0) {
                    const coords = data.features[0].geometry.coordinates;
                    const lng = coords[0];
                    const lat = coords[1];

                    // Étape 2 : Carte Leaflet + tuiles OpenStreetMap
                    const carte = L.map('carte').setView([lat, lng], 15);
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '© OpenStreetMap contributors'
                    }).addTo(carte);
                    L.marker([lat, lng])
                        .addTo(carte)
                        .bindPopup(nom + ' ' + prenom + '<br>' + adresse)
                        .openPopup();

                    // Étape 3 : API Open-Meteo → météo en temps réel
                    return fetch(
                        'https://api.open-meteo.com/v1/forecast?latitude=' + lat +
                        '&longitude=' + lng +
                        '&current_weather=true&wind_speed_unit=kmh&timezone=Europe%2FParis'
                    );

                } else {
                    // Adresse non trouvée par l'API
                    document.getElementById('meteo-contenu').textContent = 'Adresse introuvable.';
                    document.getElementById('carte').textContent = 'Impossible d\'afficher la carte.';
                }
            })
            .then(res => {
                if (res) return res.json();
            })
            .then(meteo => {
                if (!meteo) return;

                // Extraction des données météo
                const temp = meteo.current_weather.temperature;
                const vent = meteo.current_weather.windspeed;
                const code = meteo.current_weather.weathercode;

                // Affichage sécurisé via createElement (pas de innerHTML)
                const meteoContenu = document.getElementById('meteo-contenu');
                meteoContenu.replaceChildren();

                const p1 = document.createElement('p');
                p1.textContent = getIconeMeteo(code) + ' Température : ' + temp + ' °C';

                const p2 = document.createElement('p');
                p2.textContent = '💨 Vent : ' + vent + ' km/h';

                const p3 = document.createElement('p');
                p3.textContent = '📌 Conditions : ' + getDescriptionMeteo(code);

                meteoContenu.appendChild(p1);
                meteoContenu.appendChild(p2);
                meteoContenu.appendChild(p3);
            })
            .catch(err => {
                // Gestion des erreurs réseau ou API indisponible (ex: 504)
                console.error('Erreur API :', err);
                document.getElementById('meteo-contenu').textContent = 'Impossible de charger la météo.';
            });
    };

    // ─── Codes météo Open-Meteo ───────────────────────────────────────────────────

    /**
     * Retourne l'icône correspondant au code météo Open-Meteo.
     * @param {number} code - Code météo WMO (0-99)
     * @returns {string}    - Emoji représentant les conditions météo
     */
    function getIconeMeteo(code) {
        if (code === 0) return '☀️';
        if (code <= 2) return '⛅';
        if (code <= 3) return '☁️';
        if (code <= 49) return '🌫️';
        if (code <= 59) return '🌦️';
        if (code <= 69) return '🌧️';
        if (code <= 79) return '🌨️';
        if (code <= 82) return '🌧️';
        if (code <= 99) return '⛈️';
        return '🌡️';
    }

    /**
     * Retourne la description textuelle du code météo Open-Meteo.
     * @param {number} code - Code météo WMO (0-99)
     * @returns {string}    - Description en français des conditions météo
     */
    function getDescriptionMeteo(code) {
        if (code === 0) return 'Ciel dégagé';
        if (code <= 2) return 'Partiellement nuageux';
        if (code <= 3) return 'Couvert';
        if (code <= 49) return 'Brouillard';
        if (code <= 59) return 'Bruine';
        if (code <= 69) return 'Pluie';
        if (code <= 79) return 'Neige';
        if (code <= 82) return 'Averses';
        if (code <= 99) return 'Orage';
        return 'Inconnu';
    }

    // ─── Auto-initialisation carte selon la page ─────────────────────────────────

    /**
     * Détecte automatiquement si la page contient une carte (#carte).
     * Lit les données depuis le localStorage (clientAVoir ou prospectAVoir)
     * et initialise la carte + météo correspondante.
     */
    if (document.getElementById('carte')) {
        const clientData = localStorage.getItem('clientAVoir');
        const prospectData = localStorage.getItem('prospectAVoir');

        if (clientData) {
            const client = JSON.parse(clientData);
            window.initCarteMeteo(client.adresse, client.codePostal, client.ville, client.nom, client.prenom);
        } else if (prospectData) {
            const prospect = JSON.parse(prospectData);
            window.initCarteMeteo(prospect.adresse, prospect.codePostal, prospect.ville, prospect.nom, prospect.prenom);
        }
    }

});
