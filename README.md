README af Oliver

# Kør Test
Testene kan eksikveres ved at (i VSCode) høj-reklikke på mappen "src" og derefter klikke på "Run Tests with Coverage"

# Kør Programmet
Programmet kan køres ved at gå ind under filen ProjectApp.java i src\main\java\dtu\projectapp\model\ProjectApp.java


# Project Time Management Tool - GUIDE


## Eksisterende brugere
Programmet kommer med to brugere, som kan bruges til at logge ind med:
- ID: "huba"
- ID: "w"
> Begge kan benyttes.


## Home Page

Når brugeren logger ind, så kan følgende handlinger tages:
- "Log Out"
    > Logge brugeren ud
- "Add new employee"
    > Tilføj en ny bruger med et id (op til 4 symboler)
- Add project
    > Navngiv projekted, som derefter tilføjes til listen over alle projekter.
- Vælg projekt
    > Eksisterende projekter kan vælges i listen ude i højre hjørne ved at klikke på dem


## Project Page
Her kan man se navnet på det valgte projejt, projektleder og alle de aktiviteter som er tilknyttet (aktiviteter se i liste til højre, hvis der er eksisterende aktiviteter for det valgte projekt).

- "Return to homepage"
    > Brugeren sendes tilbage til start siden, hvor projekter kan vælges
- "Set project leader"
    > En projektleder kan vælges ved at indtaste et gyldigt id

- "Get Employee Status", "Get Project ETA" og "Get Full Report"
    >Disse tre rapport typer kan kun benyttes af projektlederen. De er derfor deaktiveret hvis en "ikke-projektleder" er logget ind.
        - De tre rapport typer åbner et nyt vindue, som viser relevant data

- "Add Activity"
    > Følgende data skal indtastes:
    - Aktivitens navn
    - Start dato
    - Slut dato
    - Timer det tager for aktiviteten
    - Start år
    - Slut år

- "Remove Activity"
    - Navn på aktivitet der skl slettes

- "Add Special Activity"
    > Følgende data skal indtastes/vælges
    - Vælg type af aktivitet
    - Start dato
    - Slut dato
    - Id på medarbejderen, det gælder på

- Vælg aktivitet
    > Ude i højre side kan projektets aktiviteter vælges ved at klikke på aktivitetens navn

## Activity Page
> Her kan medarbejdere og projektlederne interagere med den valgte aktivitet.

Aktivity Page viser følgende info i en sektion under kanpperne på siden:
- Aktivitetens Navn
- Timer afsat for aktiviteten
- Deadline (i uger og år)

- "Add Employee"
    > Tilføj en medarbejder til en aktivitet, ved at indtaste medarbejderens ID
- "Remove Employee"
    > Fjern en medarbejder fra en aktivitet, ved at indtaste medarbejderes ID
- "Find available employees"
    > Viser en liste over alle tilrådige medarbejdere (medarbejdere, som har under 20 aktiviter, eller medarbejdere, som ikke er tilknyttet nogle "special activities")
- "Log Work"
    > Indtast Det ID, som tiden skal registreres på, og antallet af timer, ned til 30 minutters intervaller.

- "View assigned employees"
    > Viser en liste over alle de medarbejdere, som er tilknyttet aktiviteten

- "Change activity name"
    > Indtast et nyt navn for aktiviteten

- "Change budgeted hours"
    > Ændre antallet af timer, som er afsat til aktiviteten, ved at indtaste antal timer

- "Change activity deadline"
    > Indtast uge nummer og år, som skal være den nye deadline.


