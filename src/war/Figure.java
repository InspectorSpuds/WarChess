package war;

public interface Figure {
    Cell position();

    Cell[] way(Cell dest);

    default String icon() {
        return String.format("%s.png", getClass().getSimpleName());
    }

    Figure copy(Cell dest);
}
