package agh.ics.oop;

abstract class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    public Vector2d getPosition() {
        return this.position;
    }
    public void setPosition(Vector2d position){ this.position=position;}
    public abstract String toString();
}
