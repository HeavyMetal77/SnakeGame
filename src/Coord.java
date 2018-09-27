import java.io.Serializable;

public class Coord implements Serializable {
    public int CoordX;
    public int CoordY;
    public Coord coord;

    public Coord(Coord coord) {
        CoordX = coord.CoordX;
        CoordY = coord.CoordY;
    }

    public Coord(int coordX, int coordY) {
        CoordX = coordX;
        CoordY = coordY;
    }

    public int getCoordX() {
        return CoordX;
    }

    public void setCoordX(int coordX) {
        CoordX = coordX;
    }

    public int getCoordY() {
        return CoordY;
    }

    public void setCoordY(int coordY) {
        CoordY = coordY;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "CoordX=" + CoordX +
                ", CoordY=" + CoordY +
                '}';
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return CoordX == coord.CoordX &&
                CoordY == coord.CoordY;
    }


}
