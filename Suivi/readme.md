Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part, 


# Suivi du lun. 03 févr. 2020 11:26:13 CET
Denis Conan
- [x] mettez à jour la page de titre avec vos nom et prénom
- [x] diagramme de cas d'utilisation
    - [x] « récupération... » : formuler avec plutôt une expression verbale
         qu'une expression nominale (« récupérer... ») ; c'est la graine et le
         sel de quoi ?
	- J'ai modifié en "Récupérer graine et sel d'une chambre"
- [x] précondition et postcondition
    - [x] « enregistrer... » : il manque la condition sur la forme de
         l'identifiant du badge, non ?
    - [x] « libérer... » : le terme « badge rendu » n'est-il pas plutôt dans la
         postcondition ?
    - [x] « créer un badge... » : que signifie la formulation « identifiant
         avec ce badge unique » ? c'est bizarre
	- C'était dans le sens de la négation de la postcondition : il n'existe pas de badge avec l'identifiant qui vient d'être crée. J'ai modifié la tournure de phrase pour être plus compréhensible en "badge avec cet identifiant inexistant"
- [x] table de décision des tests de validation
    - [x] à adapter selon remarques précédentes

---

# Suivi du mar. 11 févr. 2020 14:41:42 CET
Denis Conan
- merci pour l'indication des commentaires pris en compte ; je regarde les
  nouveaux éléments uniquement
- dépôt : ne mettez pas de fichier temporaire dans le dépôt
- [] diagramme de classes
    - [x] une chambre doit avoir deux clefs
    - [x] un Client occupe toujours une Chambre ?
	- 0 ou 1 chambre
    - [x] il manque le nom de l'association entre Occupation et Badge
    - [x] tout Badge est associé à une Occupation ?
- [] diagrammes de séquence
    - [] « créer une chambre » : ok
    - [] « créer occupation » : il n'y a pas de cas d'utilisation avec ce nom
         + si c'est le check-in, il manque l'identifiant du badge d'accès en
           argument
         + les dates ne sont a priori pas des chaînes de caractères ; donc,
           les vérifications « non vide » ne sont a priori pas pertinentes
         + il manque la gestion des badges d'accès
         + il manque la mise à jour des objets s et c manipulés
         + il n'y a pas d'instruction dans un diagramme de séquence, mais
           uniquement des messages, c'est-à-dire des appels d'opération ; une
           affetation d'attribut ne se montre pas dans un diagramme de séquence
- je vous encourage à continuer la modélisation en hors présentiel d'ici le
  début de la séance 4

---
