    # Utiliser une image java 17 légère 
    FROM eclipse-temurin:17-jre-alpine

    # Répertoire de travail dans le conteneur
    WORKDIR /app

    # Copier le fichier JAR de l'application (généré par Maven/Gradle)
    COPY target/*.jar /app.jar

    # Expose port pour l'app Spring Boot
    EXPOSE 8080

    # Commande d'exécution au démarrage du conteneur
    ENTRYPOINT ["java", "-jar", "/app.jar"]   