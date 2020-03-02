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
- [x] diagramme de classes
    - [x] une chambre doit avoir deux clefs
    - [x] un Client occupe toujours une Chambre ?
	- 0 ou 1 chambre
    - [x] il manque le nom de l'association entre Occupation et Badge
    - [x] tout Badge est associé à une Occupation ?
- [x] diagrammes de séquence
    - [x] « créer une chambre » : ok
    - [x] « créer occupation » : il n'y a pas de cas d'utilisation avec ce nom
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

# Suivi du lun. 02 mars 2020 19:51:42 CET
Denis Conan
- je renouvelle mes excuses pour l'oubli de suivi à la séance précédente
- diagramme de classes : ok
- diagrammes de séquence
    - [] « enregistrer... » : pb dessin : je ne comprends pas la barre
         d'activation toute seule et à cheval sur le début du premier
         fragment opt
         + vous n'avez pas décrit la séquence avant de faire le diagramme
           et vous avez oublié une partie de la précondition : par exemple
           la chambre est libre
         + les associations autour de Occupation sont non navigables vers
           l'extérieur ; donc, pourquoi donner les références en argument
           du constructeur ? en revanche, les associations sont navigables
           dans l'autre sens et les références doivent être affectées
         + la façade ne connaît pas les occupations
         + le dernier retour d'appel est positionné trop tôt
    - [] « libérer... » : il manque les dé-associations
- diagramme de machine à états : ok
- [] invariant : à exprimer en fonction des attributs : ce n'est pas
     actuellement le cas
- tables de décision des tests unitaires
    - [] constructeur : lorsque l'on crée un badge, on ne fournit pas les
         clefs en argument car les clefs sont vides
         + idem pour occupation
         + les postconditions sont locaux à la classe : écrire occupation.badge
           cela signifie que l'attribut n'est pas privé (ce que nous ne
           voulons pas) et ce n'est pas local à la classe
    - [] estDonné... : où est utilisée cette opération dans le diagramme de
         séquence « enregistrer... » ? comment alors construire la table de
         décision ?
- programmation
    - [] Badge : revoir le constructeur avec les mêmes remarques que dans la
         modélisation
         + pourquoi avoir générer tous ces getters et setters ?
    - [] Chambre : dans le constructeur, entre deux générations de clefs, le
         sel doit être incrémenté
    - Client : ok pour ce démarrage
    - [] Occupation : le constructeur ne correspond pas au diagramme de
         séquence
    - [] façade : mes méthodes chercherXxxx ne sont-elles pas simples à
         programmer ?
    - [] enregistrer... : vous avez programmé cette méthode alors que vous
         n'avez pas les premiers cas d'utilisation « créer chambre », etc. ;

- je vous encourage à continuer le projet en hors présentiel avec la
  prochaine séance ce jeudi

---
