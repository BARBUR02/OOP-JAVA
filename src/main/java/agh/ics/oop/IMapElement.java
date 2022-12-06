package agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();
    void setPosition(Vector2d position);
    default String getAnimalBox(String pos){
        return switch (pos){
            case "N" -> "src/main/resources/img/up.png";
            case "S" -> "src/main/resources/img/down.png";
            case "E" -> "src/main/resources/img/right.png";
            case "W" -> "src/main/resources/img/left.png";
            default -> "Not known";
        };

    }
    default String getGrassBox(){
        return "src/main/resources/img/grass.png";
    }

}
