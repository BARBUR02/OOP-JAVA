package agh.ics.oop;

public class Grass extends AbstractMapElement{
//    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }
//    public Vector2d getPosition(){
//        return this.position;
//    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public String toString(){
        return "*";
    }

}
