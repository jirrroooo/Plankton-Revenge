FROM maven:3.8.5-eclipse-temurin-17

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        libasound2 \
        libgl1 \
        libgtk-3-0 \
        libxi6 \
        libxrandr2 \
        libxrender1 \
        libxtst6 \
        libxxf86vm1 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY . .
RUN mvn -q -DskipTests package

CMD ["mvn", "-q", "javafx:run"]
