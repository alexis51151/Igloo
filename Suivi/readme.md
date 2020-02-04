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
- [?] précondition et postcondition
    - [x] « enregistrer... » : il manque la condition sur la forme de
         l'identifiant du badge, non ?
    - [?] « libérer... » : le terme « badge rendu » n'est-il pas plutôt dans la
         postcondition ?
	- Nous nous étions plaçé du point de vue du réceptionniste, qui doit attendre que le client lui rende le badge pour libérer la chambre
    - [x] « créer un badge... » : que signifie la formulation « identifiant
         avec ce badge unique » ? c'est bizarre
	- C'était dans le sens de la négation de la postcondition : il n'existe pas de badge avec l'identifiant qui vient d'être crée. J'ai modifié la tournure de phrase pour être plus compréhensible en "badge avec cet identifiant inexistant"
- [x] table de décision des tests de validation
    - [x] à adapter selon remarques précédentes

---
