# Plankton Revenge

> Programmer: John Rommel B. Octavo

## About
This is a game based on Java. The GUI used is JavaFX. The goal of the game is to eat more food and eat other smaller enemies to make the plankton larger than the other enemies. 

## Details

## Run locally

Requirements:
- Java 17
- Docker, if you want the containerized run path

Native Maven run:

```bash
chmod +x mvnw
./mvnw clean javafx:run
```

Docker build:

```bash
docker build -t plankton-revenge .
```

Docker GUI run on Linux:

```bash
docker run --rm -e DISPLAY="$DISPLAY" -v /tmp/.X11-unix:/tmp/.X11-unix plankton-revenge
```

Docker GUI run on macOS requires XQuartz with network clients enabled:

```bash
xhost + 127.0.0.1
docker run --rm -e DISPLAY=host.docker.internal:0 plankton-revenge
```


###### Instruction:
![Instruction 1](/src/main/java/com/game/plankton_revenge/images/Slide1.JPG)

###### Player Control:
![Player Control](/src/main/java/com/game/plankton_revenge/images/Slide2.JPG)

###### Plankton's Goal:
![Plankton's Goal](/src/main/java/com/game/plankton_revenge/images/Slide3.JPG)

###### Player Indicator:
![Player Indicator](/src/main/java/com/game/plankton_revenge/images/Slide4.JPG)

###### Plankton Enemies:
![Plankton Enemies](/src/main/java/com/game/plankton_revenge/images/Slide5.JPG)

###### Plankton Speed:
![Plankton Speed](/src/main/java/com/game/plankton_revenge/images/Slide6.JPG)

###### Power-Ups:
![Power-Ups](/src/main/java/com/game/plankton_revenge/images/Slide7.JPG)

###### Splitting Feature:
![Splitting Feature](/src/main/java/com/game/plankton_revenge/images/Slide8.JPG)

###### Game Statistics:
![Game Statistics](/src/main/java/com/game/plankton_revenge/images/Slide9.JPG)
