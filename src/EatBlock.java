import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class EatBlock implements Serializable {
    protected Color colorEatBlock;
    protected Coord coord;

    public EatBlock(Color colorEatBlock, Coord coord) {
        this.colorEatBlock = colorEatBlock;
        this.coord = coord;
    }

    public Color getColorEatBlock() {
        return colorEatBlock;
    }

    public void setColorEatBlock(Color colorEatBlock) {
        this.colorEatBlock = colorEatBlock;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "EatBlock{" +
                "colorEatBlock=" + colorEatBlock +
                ", coord=" + coord +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EatBlock eatBlock = (EatBlock) o;
        return Objects.equals(coord, eatBlock.coord);
    }
}
