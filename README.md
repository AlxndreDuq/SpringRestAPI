# TaskManager API

API REST de gestion de tâches développée avec Spring Boot.

## 🚀 Lancement du projet

Assurez-vous d'avoir **Java 17+** et **Maven 3.8+** installés.

### 🔧 Compilation & packaging

```bash
mvn clean install
```

### ✅ Lancer les tests

```bash
mvn test
```

Cela exécute les **tests unitaires** et **tests d’intégration**.

### 📊 Rapport de couverture de code

```bash
mvn jacoco:report
```

Le rapport sera disponible dans :
```
target/site/jacoco/index.html
```

### 📘 Accès à la documentation Swagger

Une fois l'application démarrée (via `mvn spring-boot:run` ou votre IDE), accédez à la documentation Swagger UI via :

```
http://localhost:8080/swagger
```

> Swagger est généré automatiquement avec Springdoc OpenAPI.

---

## 📁 Structure du projet

`main`
- `Controller/` : expose les endpoints REST
- `ControllerAdvice/` : Gère les erreurs
- `Service/` : logique métier
- `Repository/` : interfaces JPA pour la base de données
- `DTOs/` : objets de transfert
- `Entity/` : entités JPA

`test`
- `Integration/` : tests d’intégration avec MockMvc
- `Unitaire/` : tests unitaires avec Mockito

---

## 📦 Dépendances principales

- Spring Boot
- Spring Web / JPA / Validation
- H2 (en test)
- JUnit 5
- Mockito
- JaCoCo
- Springdoc OpenAPI (Swagger)

---

## 🛠️ Auteur

Développé par **Alexandre Duquesne**