# docker image build -t imageAdi .
# docker container run -d -p 4444:4444 --rm --name todos


# JDK
FROM openjdk:21

# Bilgilnedirme
LABEL maintainer="Mahmut Furkan YILDIRIM"

#Çevresel değişkenler
ENV APP_NAME="Full Stack"
ENV VERSION="v1.0.0"
ENV PORT="4444"

# Çevresel Değişkenleri RUN
RUN echo "App Name: $APP_NAME"
RUN echo "Version: $VERSION"
RUN echo "Port: $PORT"

# Kalıcılık
VOLUME /tmp

# Port
EXPOSE 4444

# mvn clean && mvn package
# Proje Jar ismini
ARG JAR_FILE=/target/*.jar

# Proje isimlednirme
ADD ${JAR_FILE} spring_react.jar

# Uygulama Başlatma Komut dizini
ENTRYPOINT ["java","-jar","/spring_react.jar"]
