------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:              MSTTRPGU
PURPOSE OF PROJECT:         SPAAAAAAAAAAAAss!!!
VERSION or DATE:            v2014-04-03-MSTTRPGU
HOW TO START THIS PROJECT:  Rechtsklick auf Fenster, dann auf new Fenster()
AUTHORS:                    Mehmet Guel, Christoph Giebenhain, Tim Kaiser    
VERSION HISTROY:

v2014-03-26-MSTTRPGU
    - Erstellen der Klassen IN DEN KATEGORIEN
        - GUI
            - FENSTER extends JFRAME
            - HAUPTMENUE extends JPANEL
            - SPIELSTART
        - MECHANIKEN
            - SPIELFELD extends JPANEL
            - OPTIONSMENÜ
            - SPIELSCHLEIFE
            - SOUND
            - ANIMATION
        - MAP
            - KARTE
            - WELT extends KARTE
            - DUNGEON extends KARTE
            - CHUNK
        - EINHEITEN
            - EINHEIT
            - SPIELER extends EINHEIT
            - NPC extends EINHEIT
            - GEGNER extends NPC
        - HINDERNISSE
            - HINDERNIS   
    
    - Fuellen der Klassen
        - FENSTER 
            - erstellt JFRAME und addet Hauptmenue
        - HAUPTMENUE
            - Menue mit den noch funktionslosen Knoepfen "SPIELSTSTART", "OPTIONEN", "CREDITS" 
                und dem Knopf "BEENDEN" der das Programm schon beendet
                

v2014-03-31-MSTTRPGU
    - Erstellen der Klasse KNOPF
        - Ziel: Anpassung der Buttons an die Fenstergroesse
        
    - Waehrenddessen:
        - Mehmet: Arbeit am Welteditor
        - Christoph:  Versuche mit Basic Movement und Vollbildmodus
        - Tim: Verzweifelt an Erstellung der Bilder
        
v2014-04-03-MSTTRPGU
    - Loeschen von SPIELSTART
    - Hinzufuegen der paintComponent() zu SPIELFELD
    - Implementieren von Runable in SPIELSCHLEIFE
    - Ueberarbeiten von KNOPF und HAUPTMENUE
        - Knoefe passen sich jetzt mit der Methode skallieren(int frameBreite, int frameHoehe) der Containergroesse 
    - Der Knopf "SPIELSTART" in HAUPTMENUE entfernt nun das Menue vom Fenster und fuegt ein SPIELFELD hinzu    
    - Einfügen des Vollbildmodus in Fenster  
    
v2014-04-13-MSTTRPGU
    - SPIELFELD besitzt nun die Methoden 
        - weltAusschnittErmitteln() 
            - liefert die sich in der Nähe des Spielers befindlichen Hindernisse und Gegner
        - einheitenPostionBerechnen()
            - ruft positionBerechnen() bei allen nahen Gegnern auf
        - zeichenReihenfolgeFestlegen()
            - bestimmt die Reihenfolge in der Objekte und Gegner gezeichnet werden
    - diese Methoden werden alle 25ms von SPIELSCHLEIFE aufgerufen
    - KARTE ließt im Editor erstellte Dateien des typs .wlt ein, erstellt aus diesen Listen mit Hindernissen und Einheiten und verteilt diese auf Chunks (WIP)
    - ZEICHENOBJEKT ist die neue Oberklasse von EINHEIT und HINDERNIS
    
v2014-04-14-MSTTRPGU
    - Einfügen des Optionsmenüs(ohne Funktionen außer wieder ins Hauptmenü zurückkehren)        
  
    
v2014-04-29-MSTTRPGU  
    - Einfügen des Creditfensters
    - Einfügen der Funktion zum Vollbild ein- und ausschalten im Optionsmenü
    - Besseres Navigieren im Optionsmenü
    
    
    
TODO:
    ✓ - Einteilung der Welt in Chunks in KARTE.levelEinlesen()
    ✓ - KARTE.getChunks() fertigstellen                        
      - HAUPTMENUE
    ✓   - Untermenüs fertigstellen
    ✓   - Titel
        - Hintergrund
        - Einstellung speichern
       (- Zusätzliche Funktionen wie Tastatursteuerung)
        - Pausenmenü
    ✓ - Tastatursteuerung
    ✓ - Einlesen von Blöcken und Einheiten fertigstellen
        (Automatische Einlesemethode? (siehe Editor))
      - SPIELER
    ✓   - Bewegung(Vektoren?)
    ✓   - Kollision mit Hindernissen (Kollisionsmasken? Pixelgenaue Kollision?)
    ✓   - Bildausschnittverschiebung
        - Angriff
        - Schaden (Gegnerkollision)
      - Gegner KI
    
      - freundlich NPCs
      - Dialoge
      - Quests
      - Items
      - Shops
      - Waffen/Rüstungen
      - Dungeons
    
      - SOUND
      - ANIMATION
    
      - Bilder für neue Gegner/Hindernisse
      - Musik/Geräusche
      - Ausarbeiten der Story
      - Leveldesign
        
BUGS
    
    
    
    
    