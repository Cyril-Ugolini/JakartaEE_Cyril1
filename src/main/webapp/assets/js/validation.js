/**
 * validation.js
 * Gestion de la validation des formulaires et de l'affichage
 * de la carte Leaflet + météo Open-Meteo.
 */

document.addEventListener('DOMContentLoaded', function () {

    // ─── Constantes métier ───────────────────────────────────────────────────────

    const METIER = {
        CA_MAX: 10_000_000_000,
        EMPLOYES_MAX: 1_000_000,
        CA_MIN: 0,
        EMPLOYES_MIN: 0
    };

    // ─── Utilitaires ─────────────────────────────────────────────────────────────

    function showFormError(formId, message) {
        const errorDiv = document.querySelector(`#${formId} #form-error`);
        if (!errorDiv) return;
        errorDiv.textContent = message;
        errorDiv.classList.remove('d-none');
    }

    function hideFormError(formId) {
        const errorDiv = document.querySelector(`#${formId} #form-error`);
        if (!errorDiv) return;
        errorDiv.classList.add('d-none');
        errorDiv.textContent = '';
    }

    function validateMetier(formId, type) {
        const errors = [];

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

    window.initFormValidation = function (formId, type) {
        const form = document.getElementById(formId);
        if (!form) return;

        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            hideFormError(formId);

            const html5Valid = form.checkValidity();
            form.classList.add('was-validated');

            const metierErrors = validateMetier(formId, type);

            if (!html5Valid) {
                showFormError(formId, 'Veuillez corriger les erreurs dans le formulaire avant de continuer.');
                return;
            }

            if (metierErrors.length > 0) {
                showFormError(formId, metierErrors.join(' '));
                return;
            }

            alert('Formulaire valide ! (envoi au serveur à connecter)');
        });

        form.querySelectorAll('input, select, textarea').forEach(input => {
            input.addEventListener('input', () => hideFormError(formId));
        });
    };

    // ─── Initialisation formulaire login ─────────────────────────────────────────

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

            alert('Connexion valide ! (authentification serveur à connecter)');
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

}); // ← FIN DU DOMContentLoaded



// ───────────────────────────────────────────────────────────────────────────────
//  FONCTION initCarteMeteo SORTIE DU DOMContentLoaded (IMPORTANT !)
// ───────────────────────────────────────────────────────────────────────────────

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

                // Étape 2 : Carte Leaflet
                const carte = L.map('carte').setView([lat, lng], 15);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '© OpenStreetMap contributors'
                }).addTo(carte);

                L.marker([lat, lng])
                    .addTo(carte)
                    .bindPopup(nom + ' ' + prenom + '<br>' + adresse)
                    .openPopup();

                // Étape 3 : Météo
                return fetch(
                    'https://api.open-meteo.com/v1/forecast?latitude=' + lat +
                    '&longitude=' + lng +
                    '&current_weather=true&wind_speed_unit=kmh&timezone=Europe%2FParis'
                );

            } else {
                document.getElementById('meteo-contenu').textContent = 'Adresse introuvable.';
                document.getElementById('carte').textContent = 'Impossible d\'afficher la carte.';
            }
        })
        .then(res => res && res.json())
        .then(meteo => {
            if (!meteo) return;

            const temp = meteo.current_weather.temperature;
            const vent = meteo.current_weather.windspeed;
            const code = meteo.current_weather.weathercode;

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
            console.error('Erreur API :', err);
            document.getElementById('meteo-contenu').textContent = 'Impossible de charger la météo.';
        });
};


// ─── Fonctions météo ───────────────────────────────────────────────────────────

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
