# Tank War Game

## Introduction
Tank War Game is a 2D action game developed using Java and the JavaFX framework, incorporating FXML to separate the user interface design from the game logic. Players control tanks to navigate through various obstacles and engage enemy tanks controlled by AI. The game features engaging gameplay elements such as strategic movement, missile firing, and dynamic collision detection with terrain and other objects. Leveraging FXML enhances maintainability and scalability by clearly delineating UI development from game mechanics. This game also implements multiple object-oriented design patterns, including Abstract Factory, Observer, and Singleton, to organize code more efficiently and promote modular development.

## System Requirements
- **Java**: Version 11 or higher
- **JavaFX SDK**: Compatible with your Java version
- **IDE**: Supports Java and JavaFX (e.g., IntelliJ IDEA or Eclipse)

## Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/nicktull/TankWarGame.git
2. **Navigate to the project directory**
   ```bash
   cd tankwargame

## Setup
1. **Import the project into your IDE**
For IntelliJ IDEA: Open -> Select the tankwargame directory -> OK
For Eclipse: File -> Import -> Existing Projects into Workspace -> Select tankwargame directory

2. **Configure JavaFX in your IDE:**
Ensure JavaFX libraries are added to your project settings and configure VM options to include the path to javafx.controls and javafx.fxml.

## Running the Program
1. Download the jar executable labeled TankWarGame-1.0-SNAPSHOT.jar

   - https://github.com/nicktull/TankWarGame/tree/main/Jar

2. Locate your path to the JavaFX SDK lib directory and copy it to your clipboard.
3. Locate your jar file
4. Once you have copied that path, run the following command:
```bash
java --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml -jar TankWarGame-1.0-SNAPSHOT.jar
```

## How to Play
- Use the arrow keys to move the tank:
- Up (↑), Down (↓), Left (←), Right (→)
  - Press SPACEBAR to fire missiles.
- Destroy all enemy tanks to win.

## UML Diagram
![image](https://github.com/user-attachments/assets/3d8f9297-ed0a-4daa-b519-6de062e87063)


## Mermaid.js Link
https://www.mermaidchart.com/app/projects/00de19cb-61b4-4256-bc14-00f5ed4be153/diagrams/64f664e6-c8fe-4250-9ee3-f789e70e29df/version/v0.1/edit

## Credits
Nicholas Tullbane
