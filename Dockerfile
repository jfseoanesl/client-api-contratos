FROM amazoncorretto:17-alpine-jdk
COPY target/GestContractClient-fx-1.jar GestContractClient-fx-1.jar
COPY target/lib /lib/
ENTRYPOINT ["java", "-jar", "/GestContractClient-fx-1.jar"]
#ENTRYPOINT java --module-path /javafx-sdk-14.0.2.1/lib --add-modules javafx.controls,javafx.fxml -jar toDoList.jar -Dprism.verbose=true