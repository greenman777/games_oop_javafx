package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void checkPositionFigure() {
        Figure bishopBlack = new BishopBlack(Cell.D8);
        assertThat(bishopBlack.position(), is(Cell.D8));
    }

    @Test
    public void checkCopyFigure() {
        Figure bishopBlack = new BishopBlack(Cell.D8);
        Figure newBishopBlack = bishopBlack.copy(Cell.B6);
        assertThat(newBishopBlack.position(), is(Cell.B6));
    }

    @Test
    public void checkWayDiagonallyFigure() {
        Cell[] result = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Cell[] cells = bishopBlack.way(Cell.G5);
        assertThat(cells, is(result));
    }

    @Test
    public void checkWayNotDiagonallyFigure() {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    new BishopBlack(Cell.C1).way(Cell.E5);
                });
        assertThat(exception.getMessage(), is("Could not move by diagonal from C1 to E5"));
    }
}