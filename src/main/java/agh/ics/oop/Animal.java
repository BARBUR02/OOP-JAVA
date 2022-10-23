package agh.ics.oop;

public class Animal {
    private MapDirection orient;
    private Vector2d currPosition;

    public Animal() {
        this.orient = MapDirection.NORTH;
        this.currPosition = new Vector2d(2, 2);
    }

    public MapDirection getOrient() {
        return orient;
    }

    public Vector2d getCurrPosition() {
        return currPosition;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "orient=" + orient +
                ", actualPosition=" + currPosition +
                '}';
    }

    public boolean isAt(Vector2d position) {
        return this.currPosition.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d vector = this.currPosition;
        if (direction==MoveDirection.FORWARD || direction==MoveDirection.BACKWARD) {
            if (vector.x <= 0 && this.orient == MapDirection.WEST && direction==MoveDirection.FORWARD ||
                    vector.x >= 4 && this.orient == MapDirection.EAST && direction==MoveDirection.FORWARD)
                return;
            if (vector.x >= 4 && this.orient == MapDirection.WEST && direction==MoveDirection.BACKWARD ||
                    vector.x <= 0 && this.orient == MapDirection.EAST && direction==MoveDirection.BACKWARD)
                return;
            if (vector.y <= 0 && this.orient == MapDirection.SOUTH && direction==MoveDirection.FORWARD||
                    vector.y >= 4 && this.orient == MapDirection.NORTH && direction==MoveDirection.FORWARD)
                return;
            if (vector.y >= 4 && this.orient == MapDirection.SOUTH && direction==MoveDirection.BACKWARD||
                    vector.y <= 0 && this.orient == MapDirection.NORTH && direction==MoveDirection.BACKWARD)
                return;
        }
        switch (direction) {
            case RIGHT -> this.orient = this.orient.next();
            case LEFT -> this.orient = this.orient.previous();
            case FORWARD -> {
                switch (this.orient) {
                    case NORTH -> this.currPosition = new Vector2d(vector.x, vector.y + 1);
                    case SOUTH -> this.currPosition = new Vector2d(vector.x, vector.y - 1);
                    case EAST -> this.currPosition = new Vector2d(vector.x + 1, vector.y);
                    case WEST -> this.currPosition = new Vector2d(vector.x - 1, vector.y);
                }
            }
            case BACKWARD -> {
                switch (this.orient) {
                    case NORTH -> this.currPosition = new Vector2d(vector.x, vector.y - 1);
                    case SOUTH -> this.currPosition = new Vector2d(vector.x, vector.y + 1);
                    case EAST -> this.currPosition = new Vector2d(vector.x - 1, vector.y);
                    case WEST -> this.currPosition = new Vector2d(vector.x + 1, vector.y);
                }
            }
        }
    }
}
