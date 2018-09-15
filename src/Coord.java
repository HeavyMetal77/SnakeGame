public class Coord {
    public int CoordX;
    public int CoordY;

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
}
