Aufgabenstellung:
Das Rucksackproblem ist eine in der Informatik bekannte Problemstellung.
Stell Dir vor, Du stehst am Check-in-Schalter von "Knapsack"-Airlines und erfährst, dass Du Deine einzelnen Gepäckstücke, die unterschiedlich wertvoll sind, in Deinem Rucksack verstauen musst. Dein Rucksack darf insgesamt maximal 15 kg wiegen, sonst geht er nicht mehr als Handgepäck durch und wird abgewiesen. Es ist klar, dass Du nicht alle Gepäckstücke wirst mitnehmen können. Du musst nun eine Lösung finden, wie Du Deinen Rucksack am besten packst, um die wichtigsten Gepäckstücke mitzunehmen.
Gegeben sind also n Gepäckstücke mit den Eigenschaften Gewicht und Wert. Außerdem gegeben ist eine Grenze (15kg), die nicht überschritten werden darf. Gesucht ist die bestmögliche Kombination von Gegenständen, die das Grenzgewicht nicht überschreitet und den höchsten Gesamtwert hat.
Als Herangehensweise bieten sich hier näherungsweise Lösungen optimal an, da eine exakte Lösung für größere n aufgrund der Laufzeitkomplexität von O(2^n) ungeeignet ist.
Input:
Deine Abgabe soll:
Das Rucksackproblem mit den Variablen n = Anzahl der Gegenstände und m = Maximalgewicht des Rucksacks gelöst werden.
Die Werte der Variablen sollen über die Konsole oder eine Eingabemaske sinnvoll gesetzt werden können (die Werte der Eigenschaften der Gegenstände können generiert werden).
Dein Algorithmus soll:
•	n Gegenstände in einen Rucksack packen und dabei den bestmöglichen Gesamtwert erreichen.
•	Jeder Gegenstand hat die Eigenschaften Gewicht g (in kg) und Wert w (in Euro)
•	Der Rucksack darf nur ein Maximalgewicht von m (in kg) haben
•	Sowohl bei Gewicht (kg) als auch beim Wert (Euro) handelt es sich um positive ganze Zahlen. 
Dokumentation:
Die Dokumentation erfolgt anhand von Screenshots und wird fortfolgend beschrieben. Das hier dargestellte Beispiel ist bereits mit Demo-Datensätzen bespielt, sodass eine bessere Erläuterung erfolgen kann.
1.	Start der Applikation
Die Applikation wurde als JavaFX Applikation entwickelt und kann entweder aus einer IDE heraus gestartet werden, die JavaFX unterstützt oder es kann direkt die mitgelieferte .JAR Datei ausgeführt werden.
Die JAR-Datei liegt im Verzeichnis: 
Coding-Competition\CodingCompetition_Rucksackproblem\dist
2.	Nach dem Start der Applikation
Die Applikation startet und zeigt einem eine GUI. Die GUI besitzt verschiedene Ansichten. Diese Ansichten können über den Reiter links durch die Symbole (Eimer mit Elementen, Rucksack oder Rechner) erreicht werden.
a.	Eimer mit Elementen = Anlage, Löschen und autogenerieren von Gegenständen die in den Rucksack gepackt werden können.
b.	Rucksack = Anlage, Löschen und autogenerieren eines Rucksacks. Der Rucksack kann die Elemente enthalten.
c.	Rechner = Auswahl der Gegenstände die geprüft werden sollen mit einem zugehörigen Rucksack, sowie die Auswahl welcher Algorithmus ausgeführt werden soll.
 

 
3.	Neuen Gegenstand anlegen
Ein Neuer Gegenstand kann auf zwei Arten angelegt werden:
a.	Zufallsgenerator = Erzeugt einen neuen Gegenstand mit Werten und Gewicht zwischen 1 und 100! Der Name des Gegenstands wird auf Autogen. Gegenstand gesetzt
 
b.	Hinzufügen = Neue Maske in der explizit die Werte angegeben werden können!

 

4.	Neuen Rucksack anlegen
Die Anlage eines Rucksacks erfolgt analog zur Anlage der Gegenstände.
 
5.	Algorithmen ausführen
Um eine Algorithmus ausführen zu können muss auf den Reiter des Rechners geklickt werden.
Hier erscheint folgende Ansicht:
In der linken Tabelle werden alle möglichen Gegenstände angezeigt. Alle erstellten Gegenstände die in einen Rucksack gepackt werden können werden hier lediglich angezeigt. Durch halten der STRG-Taste kann eine Mehrfachselektion der gewünschten Elemente erfolgen.
Die rechte Tabelle lässt lediglich eine Auswahl zu und repräsentiert die Rucksäcke die im System angelegt wurden. Hier soll der Rucksack ausgewählt werden, in den die Gegenstände gepackt werden sollen.

 
Im roten Kasten sind die möglichen Algorithmen zur Lösung des Rucksackproblems ersichtlich. Es wurden ein Greedy-Algorithmus, ein Profit-Index-Algorithmus und ein Approximations-Algorithmus implementiert. 
6.	Besichtigung des Ergebnisses des Algorithmus
Nach einem Klick auf den Button Berechnen wird der Algorithmus ausgeführt. 
 Anschließend wird ein Log angezeigt. Hier wird im Titel der gewählte Algorithmus gezeigt und anschließend werden die Rucksack-Informationen nochmals visualisiert. Unten in der Detailansicht werden dann die Gegenstände die eingepackt wurden dargestellt.

 
Eingesetzte Algorithmen:
1.	Greedy Algorithmus
Der Greedy Algorithmus nimmt keine kluge Sortierung und Algorithmik in Bezug auf das Rucksackproblem an.
Nach dem FiFo Prinzip werden die Elemente in den Rucksack gepackt. Lediglich die Prüfung ob das Element hinein passt wird gemacht. Die Lösung ist sehr schnell aber liefert in der Praxis schlechte Ergebnisse.
Der Algorithmus ist in der Java-Klasse: GreedyAlgorithm
2.	Profit Index Algorithmus
Der Profit-Index Algorithmus nimmt eine Sortierung der Gegenstände die mitgenommen werden sollen vor. Dabei bedient sich er Algorithmus eines Profitabilitätsindex. Dieser errechnet sich aus :
Profit Index = Wert des Gegenstands / Gewicht des Gegenstands
Anschließend werden die Elemente nach dem Index sortiert und eingepackt.
Der Algorithmus ist in der Java-Klasse: ProfiIndexAlgorithm

3.	Approximations-Lösung
Die Approximationslösung versucht nicht den bestmöglichen Wert zu finden, sondern will sich dieser lediglich annähern. 
Hierzu wurde ein Algorithmus der sich rekursiv Aufruft implementiert um die möglichen Kombinationen zu testen und sich schrittweise dem Problem zu nähern. 
Der Algorithmus ist in der Java-Klasse: ApproximateAlgorithmus
 
Eingesetzte Technologien:
1.	JavaFX
2.	SQLite
