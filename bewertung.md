Hoi Florian,

danke für das coole Projekt!
Idee und Ansatz sind sehr gut. Mit genauerer Analyse des Vorgehens und konsequenter Umsetzung kannst du deutlich mehr erreichen. Bitte arbeite genauer, und schaue im Bewertungsraster wo du Punkte holen kannst. Das empfehle ich dir ganz fest für die IPA, damit die Ausbildung zum Erfolg führt. Hier mein Befund nach zwei Stunden intensiven Debugging:

| Bewertungsraster | Max-Punkte | Florian | Kommentar |
| --- | --- | --- | --- |
| Dokumentation sinnvoll strukturiert und durchgängig einheitlich formatiert | 2 | 1 | Inhalt nicht stringent aufgebaut, weiter Interpretationsspielraum durch Füllhorn verwendeter  Icons und Durchstreichungen |
| notwendige User Stories mit Akzeptanzkriterien aussagekräftig und korrekt dokumentiert | 2 | 1 | Begründung der Notwendigkeit der Userstories fehlt: als x möchte ich y weil z. |
| Arbeitsplan enthält sinnvolle Arbeitspakete und Aufwandsschätzung | 2 | 1 | Aufwandsschätzung fehlt |
| Backend funktional und korrekt implementiert und im Code dokumentiert | 3 | 1 | siehe Bewertung |
| mind. 2 sinnvolle Backend-Tests automatisiert und protokolliert | 2 | 1 | Unit-Test vorhanden, dysfunktional; Postman-Export vorhanden aber unvollständig |
| Backend-Architektur aussagekräftig, korrekt und mit eigenen Worten beschrieben (ggf. illustriert) | 2 | 2 | ok |
| Transaktionen begründet eingesetzt | 2 | 0 | fehlt |
| Frontend funktional und korrekt implementiert und im Code dokumentiert | 3 | 1 | siehe Bewertung |
| mind. sinnvolle 2 Frontend-Tests automatisiert und protokolliert | 2 | 0 | fehlt |
| Frontend-Architektur aussagekräftig, korrekt und mit eigenen Worten beschrieben (ggf. illustriert) | 2 | 2 | ok |
| Git korrekt eingesetzt (mit Branches, mehreren ordentlich kommentierte Commits) | 2 | 2 | ok |
| JWT-Authentifizierung im Front- und Backend korrekt implementiert und im Code dokumentiert | 2 | 2 | ok (Logout fehlt) |
| Sicherheitskonzept dokumentiert | 2 | 0 | fehlt (Kapitel Security Hinweis ist nicht zureichend) |
| Arbeitsjournal je Block und Teammitglied (wer hat was gemacht, wie lange hat es gedauert) | 2 | 2 | knapp ok - wie lange hast du wirklich jeweils daran geschafft? |
| Auswertung enthält objektiven Soll-Ist Vergleich und Problemanalyse | 2 | 2 | ok. Und was machst du zukünftig besser? |
| Projekt ist nach Anleitung deploybar und ausführbar | 2 | 1 | siehe Bewertung |
| Live Produktpräsentation (max. 10 Minuten) 1p Einleitung, Gesamteindruck 1p verständliche Sprache 1p roter Faden 1p Prod.-Demo 1p spannende Aspekte, korrekt 1p Zeit eingehalten | 6 | 4 | Vortrag wirkt anfangs gut motiviert, aber nach 5 Minuten bereits fertig (Zeit nicht eingehalten -1), dann noch ein bisschen durch Doku gescrollt – das ist wenig zielführend. Sauberer Anfang, bitte auch sauber abschliessen (Gesamteindruck -1) |
| Fachgespräch (individuell) | 10 | 5 | ALternative zum Pre-Authorize? --> nicht gewusst Vorgehen Debugging von Frontend & Backend? --> fokussiert auf Frontend; Postman Call ; kann den Postman-Call nur mit viel Hilfe ausführen |
| Summe | 50 | 28 |  |
| Note |  | 3.8 |


# Backend

* Compilerfehler wegen ungültigen UTF8 Zeichen im App.properties (fixed that for u)
* Compilerfehler in RatingControllerTest, weil Setter und Getter in RatingDTO fehlen (fixed that for u)
* Compilerfehler im test/../Watchlist/test.java, Ursache: fehlerhafte Verschachtelung der Packages (fixed that for u)

* Unit-Tests laufen nicht wegen fehlender Vorbedingungen (did not fixed that for u)

* modelliertes Datenmodell wurde nicht korrekt umgesetzt (vermutlich Ursache für das gesamte Durcheinander und die fehlerhafte Darstellung und Funktionalität im Frontend)

# Frontend

* coole Toasts, coole TMDB-Integration!
* package.json unvollständig (Projekt nach `npm install` nicht ausführbar wegen fehlender package: @rollup, @tailwindcss/oxide (fixed that for u)
* Watchlist anschauen:
	- Zugriff auf nicht definierten API-Endpoint (fixed that for u)
	- Darstellung versucht auf nicht definierte Attribute der Watchlist - Entries zuzugreifen
* Film zur Watchlist hinzufügen: funktioniert an sich schon, aber... Backend passt nicht dazu

# Doku

* Datenmodell mit PlantUML toll (ab besten im Planung-Kapitel)
* Aufbau Inhalts-VZ wirkt nicht logisch (warum stehen die User-Stories erst am Ende?) --> besser nach IPERKA ausrichten (dann wird es automatisch richtig)
* Testplanung und Testdurchführung sind zwei grundverschiedene Tätigkeiten (und auch Abschnitte in der Doku)
* Kapitel *Wichtige Backend-Klassen* enthält "einfach nur" zwei unkommentierte Funktionen
* Bekannte Probleme: was ist mit den Durchstreichungen gemeint? Ist das behoben?
* Koffer - Thema "Use Cases und Tests" enthält weder für das eine noch das andere Thema brauchbare Informationen
* Integrationstests:  gelieferte Postman-File enthält die aufgeführten Tests nicht vollständig