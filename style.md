# STYLE DE CODE JAVA

Le style d'écriture de notre programme Java devra respecter quelques règles pour s'assurer de la conformité, la propreté et la bonne compréhension du code.

## Noms

Pour permettre une bonne compréhension du code, les variables, les méthodes et les classes devront respecter un certain style pour leurs noms.

##### Variables

Les variables doivent se nommer d'une façon où aucun commentaire est nécéssaire pour expliquer leurs utilités. Elles doivent également commencer par une lettre minuscule suivis d'une majuscule pour les autres mots de la variable en question. Les noms de variables ne doivent pas contenir de symboles, par exemple `_` et `é`. Les constantes devront respecter le format "SCREAMING_SNAKE_CASE"

Mauvais | Bon 
--- | --- 
int i = 1; | int nbClient = 1;
boolean true_false = true; | boolean ecranAllume = true;
String chaineDeChar = "abcd"; | String messageImportant = "abcd;"
final String nom_auteur = "David"; | final String NOM_AUTEUR = "David";
final int NUMERODETELEPHONE = 1234567890; | final int NUMERO_DE_TELEPHONE = 1234567890;

#### Méthodes

Les noms de méthodes devront respecter le format "camelCase".

Mauvais |  Bon
--- | --- 
Getid() | getId()


Si la méthode retourne une variable, le nom de la méthode devrait contenir de l'information sur la variable retourné pour être en mesure de bien comprendre et utilisé la méthode en question.

Mauvais |  Bon
--- | --- 
calcul() | prixTotal()

Une méthode qui utilise une variable pour effectuer un action doit toujours commencer par un verbe. 

Mauvais |  Bon
--- | --- 
client(int id) | rechercheClient(int id)


#### Classes

Le nom des classes devront respecter le format "PascalCase" pour les différencier des variables.

Mauvais |  Bon
--- | --- 
animalDeCompagnie | AnimalDeCompagnie


Le nom des classes de tests commencerons par un `_` et terminerons par `Test` pour les différencier des autres classes.

Mauvais |  Bon
--- | --- 
Personne_2 | _PersonneTest


#### Fichiers 

Le nom des fichiers devront respecter le format "kebab-case" pour avoir de la facilité à les différencier des autres objets

Mauvais |  Bon
--- | --- 
fichierteststats1 | fichiertest-stats-1

## Commentaires

Pour s'assurer d'une propreté de code exemplaire, les commentaires doivent suivre plusieurs règles.

#### Signature de classes

Chaque classes doivent avoir une signature de classe au début du fichier comportant les informations suivantes:
1. Une description brève de la classe.
2. Le nom de l'auteur de la classes ou les noms si plusieurs programmeurs sont responsables de la même classe.
3. Le code permanent ainsi que le courriel de l'auteur devra suivre son nom.
4. Le sigle du cours pour laquelle la classe sera utilisé et le groupe.
5. La date de la dernière modification de la classe. (YYYY-MM-DD)
6. Les commentaires légals

Voici un exemple d'une bonne signature de classe

```
/** 
 *Cette classe contient le main a executer pour le jeu de roche papier ciseau. Cette classe permet de gerer les matchs entre joueurs.
 *
 * @author David Daoud
 * Code permanent: DAOD80070006
 * Courriel: daoud.david@courrier.uqam.ca

 * @author Loic Dion-Plourde
 * Code permanent: DIOL27099802
 * Courriel: dion-plourde.loic@courrier.uqam.ca

 * Cours: inf2050-gr30
 * @version 2021-02-14
 
 * Copyright (C)  2021 by David Daoud,Loic Dion-Plourde, All right reserved.
 * Released under the terms of the GNU general Public License version 2 or later.
 */
```

#### Signature de méthode

Chaque méthode doivent avoir une signature de classe au début de la méthode comportant les informations suivantes:

1. Une description brève de la classe.
2. Le nom de l'auteur de la méthode ou les noms si plusieurs programmeurs sont responssables de la même méthode.
3. Une description brève de tout les paramètres que la méthode reçoit.
4. Une description brève de qu'est-ce que la méthode retourne.

Voici un exemple d'une bonne signature de méthode:

```
/**
 * Cette methode demande à l'utilisateur de rentrer son nom et prenom pour ensuite lui retourner sont prix a payer.
 * @author David Daoud
 * 
 * @param nom Le nom de famille du client
 * @param prenom Le prenom du client
 * @param mssErr Le message d'erreur a afficher lorsque le client n'est pas dans la base de données.
 * @return le prix à payer du client
 */
```

Cette signature n'est pas demandé lorsque la méthode ne comporte aucune logique. Par exemple, les get, set et les constructeurs.

#### Description de variables

Aucun commentaire est requis pour décrire une variable. Le nom de la variable devrait être suffisant pour expliquer son utilité.

#### Description logique

Un commentaire est fortement conseillé lorsque le code contient de la logique ou un algorithme complexe pour que les autres programmeurs puissent bien comprendre le code ainsi que l'enseignant et les correcteurs.


## Indentation

L'indentation du code est important pour la compréhension et la propreté du code. Voici certains points pour respecter l'indentation:

1. Bien séparer la déclarations de variables de tout autres codes pour facilement les retrouver.
2. Augmenter l'indentation de 1 lorsque le code rentre dans un conteneur. Faire un `Enter` pour le premier niveau d'indentation.
3. Faire un `enter` à chaque fois que l'indentation diminue d'un niveau.
4. Bien séparer le return de la méthode pour facilement voir qu'est-ce qui est retourné.



Voici un exemple d'indentation conforme:

```
public Client rechercheClientDansTableau(Client[] tab, int id) {
    
    String message = "Erreur!";
    Boolean clientTrouve = false;
    Client client;
    
    for (int i = 0; i < tab.length; i++) {
    
        if (tab[i].id == id) {
            clientTrouve = true;
            client = tab[i].id;
            Message = "Client trouve!";
        }
        
        System.out.println(i)
    }
    
    System.out.println(message);

    return client;
}
```

Certaines méthodes ne demandent pas de suivre les restrictions plus haut, tel que les constructeurs, les get et les set. Également, lorsque la méthode ne comporte pas de logique, l'indentation est optionnelle.

## Langue

Le code sera uniquement écrit en français pour une compréhension et une conformité exemplaire. Aucun "franglais" n'est permis. Uniquement les termes informatique sont permis en anglais et les noms d'objets peuvent porter les mêmes noms de la classe. Par exemple, Scanner scanner = new Scanner();

## Autre

Chaque ligne de code devra comporter un maximum d'environ 120 caractères. Cela permet la facilité de lecture et d'édition du code. Chaque méthode ne devrait pas contenir plus de 15 lignes de code. Cela permet d'avoir uniquement une utilité pour la méthode. 
