# rest-versioning

SpringBoot/Swagger/OpenAPI

Dépendances pour implémenter l'OpenAPI via Swagger :

        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.1.0</version>
        </dependency>

Or:


        <dependency>--
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>3.0.0</version>
       </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-spring-web</artifactId>
            <version>3.0.0</version>
        </dependency>


# Notre exemple de versioning : URI & Header.

Il expose les méthodes CRUD de base pour la gestion d'une Commune. 
Trois versions de l'API sont disponibles pour les clients externes : *1.0*, *1.1* et *1.2*. 
Dans la version *1.1*, j'ai changé la méthode de mise à jour de l'entité Commune. 
Dans la version *1.0*, il était disponible sous le chemin ***/commune***, alors qu'il est maintenant disponible sous le chemin ***/commune/{codePostal}***. 
C'est la seule différence entre les versions *1.0* et *1.1*. 
Il existe également une seule différence dans l'API entre les versions *1.1* et *1.2*. 
Au lieu du champ ***anciennete***, il renvoie un nouveau champ ***age***. Cette modification affecte tous les points 
de terminaison (endpoints) à l'exception de DELETE ***/commune/{codePostal}***. 
