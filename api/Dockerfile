# ---- build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 依存関係ダウンロードをキャッシュしやすくするため、先にGradle周りだけコピー
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle* settings.gradle* ./

RUN chmod +x ./gradlew
RUN ./gradlew --no-daemon dependencies

# ソースをコピーしてJar作成
COPY src ./src
RUN ./gradlew --no-daemon bootJar -x test

# ---- run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]