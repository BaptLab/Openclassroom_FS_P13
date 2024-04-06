# Openclassroom_FS_P13

# Introduction

Projet n°13 de la formation de Développeur Full-Stack - Java et Angular d'Openclassroom (2023/2024).

Il s'agit d'une PoC (Proof of Concept) d'une application fullstack de chat instantané avec fonctionnalité de salle de discussion. Les utilisateurs doivent s'authentifier via un portail, puis peuvent accéder à une salle de chat pour des communications instantanées. La base de données MySQL est utilisée pour stocker les informations, créée notamment à l'aide d'un script présent dans le dossier ressources du répertoire racine de ce GitHub.

# Front

## Installation

Ce projet a été généré avec [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

Assurez-vous de l'installer depuis le dossier /front avant de poursuivre avec la commande :

```bash
npm install -g @angular/cli@14.1.3
```

N'oubliez pas d'installer les dépendances en exécutant la commande suivante dans le répertoire du projet :

```bash
npm install
```

## Serveur de Développement

Pour lancer le serveur de développement, utilisez la commande suivante :

```bash
ng serve
```

Accédez à http://localhost:4200/ dans votre navigateur.

# Back

## Installation

Ce projet a été développé en utilisant Java, Spring et MySQL. Assurez-vous d'avoir installé les éléments suivants :

- [Java Development Kit (JDK)](https://www.oracle.com/fr/java/technologies/downloads/) version 8 ou supérieure.
- [MySQL ou MySQL Workbench](https://www.mysql.com/fr/downloads/) installé sur votre machine.
- [Maven](https://maven.apache.org/) installé sur votre machine.

### Installation des dépendances

Avant de lancer l'application, assurez-vous d'installer les dépendances en utilisant Maven depuis le dossier /back. Exécutez la commande suivante dans le répertoire du projet :

```bash
mvn install
```

Cette commande téléchargera et installera toutes les dépendances nécessaires au projet.

### Configuration de la base de données

Créez une base de données MySQL avec le nom de votre choix (nom_de_votre_base_de_données).

Assurez-vous que le fichier application.properties dans le projet Spring contient les informations correctes pour la base de données. Modifiez les paramètres suivants en fonction de votre configuration :

`spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_votre_base_de_données?useSSL=false&serverTimezone=UTC`

`spring.datasource.username=votre_utilisateur`

`spring.datasource.password=votre_mot_de_passe`

Configurez la base de données MySQL en utilisant le fichier sql script.sql présent dans le dossier front-end/ressources/sql.
Vous pouvez utiliser un outil tel que MySQL Workbench pour exécuter ce script.

### Lancement de l'application

Lancez l'application Spring Boot en utilisant la commande Maven suivante dans le terminal :

```bash
mvn spring-boot:run
```

Ou

Lancez l'application Spring Boot depuis votre IDE :

Dans Eclipse : Cliquez avec le bouton droit sur le projet > Run As > Spring Boot App.
Dans IntelliJ : Cliquez sur le bouton Run à côté de la classe principale.

Cela lancera le serveur back-end à l'adresse http://localhost:8080/.

Assurez-vous que le serveur back-end est en cours d'exécution avant de lancer l'application front-end.

## Informations supplémentaires

- Ce projet utilise Angular et Spring Boot pour le front-end et le back-end respectivement. Assurez-vous de vérifier la compatibilité lors de mises à jour ou de l'installation de packages supplémentaires.
- N'hésitez pas à personnaliser le projet en fonction de vos besoins spécifiques.
- Pour plus d'informations sur les commandes Angular CLI et la structure du projet, consultez la documentation officielle d'Angular.
- Pour plus d'informations sur Spring Boot, référez-vous à la documentation officielle de Spring Boot : Documentation Spring Boot.

## Licence

Ce projet est sous [licence MIT](https://chat.openai.com/c/LICENSE) - consultez le fichier [LICENSE](https://chat.openai.com/c/LICENSE) pour plus de détails.
