# Introductie
Deze Demo geeft een beeld van hoe Dependency Injection zelf te implementeren is en is deel van de BEWD Course aan de 
Hogeschool Arnhem/Nijmegen. 

De manier waarop Spring zelf instanties maakt van klassen geannoteerd met `@Component`, `@Service` of `@RestController`, 
of hoe Dependency Injection werkt, is voor veel Junior Software Developers magisch. Dit project geeft inzicht in hoe 
die magie werkt, aangezien alles is uitgewerkt in eigenn code.

# Uitleg
Dit is geen Spring (Boot), maar een gewone console-applicatie. Er wordt dan ook niet gebruik gemaakt van Spring
annotaties. Het project bevat een Resource en een Service laag en moet opgestart
worden via een `main()`-methode uit de klasse `DiyDIRunner`. Daarna volgen de volgende stappen:

* De `main()`-methode zoekt naar Klassen die geannoteerd zijn met `@DiyRestController` en maakt daar instanties van.
* Binnen deze instanties wordt gezocht naar methoden die geannoteerd zijn met `DiyAutowired`. 
* Van de gevonden methodes wordt het type van de parameter bepaald, waarna gezocht wordt naar een klasse van dat type.
* Van deze klasse wordt een instantie gemaakt, die vervolgens gebruikt wordt om the injecteren.
* Nu wordt er gezocht naar methoden die geannoteerd zijn met `@DiyRequestMapping`. Deze worden aangeroepen.
