# Test technique Ekwateur
Test technique pour la société ekwateur

Réalisation de l'exercice se trouvant ici : https://gitlab.com/ekwa-technical-tests/test-spring
## Architecture de l'application
Utilisation de l'architecture hexagonal pour résoudre le problème

### Domain
Le package domain contient les entités métier de l'application :
- Offer : objet représentant une offre
- Code : objet représentant une code promo
- OfferType : type de l'offre
- Validator : Patron strategy qui contient la logique de recherche d'offre compatible à une offre
- ValidatorFactory : Patron factory à qui on délègue la création de strategy

### Infrastructure
Contient les adapters out de récupération des données via l'api ekwateur et une classe utilitaire qui gère les appels http

### Application
Contient les use case du domain

Commande pour lancer l'application:
- mvn spring-boot:run -Dspring-boot.run.arguments=MON_CODE_PROMO_A_TESTER
- java -jar yourJar.jar MON_CODE_PROMO_A_TESTER

### Presenter
Contient une classe presenter/controller qui permet l'utilisation du use case et renvoi le résultat sous forme de fichier

