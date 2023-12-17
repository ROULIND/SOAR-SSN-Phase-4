// certified.js

// Définir une variable pour stocker le nombre de followers
var followersCountPlaceholder;

// Changer le nombre de followers requis pour la certification
if (followersCountPlaceholder >= 1) {
    document.getElementById('certificationImage').style.display = 'inline-block';
    document.getElementById('texte_certifie').innerHTML  = 'Certified influencer';
} else {
    document.getElementById('certificationImage').style.display = 'none';
    document.getElementById('texte_certifie').innerHTML  = '';
}
