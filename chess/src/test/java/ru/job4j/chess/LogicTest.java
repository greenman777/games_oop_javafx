package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;

public class LogicTest {

    @Test
    public void checkFigureNotFoundException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        FigureNotFoundException exception = assertThrows(
                FigureNotFoundException.class,
                () -> {
                    logic.move(Cell.C3, Cell.H6);
                });
    }

    @Test
    public void checkOccupiedCellException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        logic.add(new PawnBlack(Cell.E7));
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class,
                () -> {
                    logic.move(Cell.F8, Cell.E7);
                });
        assertThat(exception.getMessage(),
                is("Your move is hindered by a figure on the square E7"));
    }

    @Test
    public void checkImpossibleMoveException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    logic.move(Cell.F8, Cell.E6);
                });
        assertThat(exception.getMessage(),
                is("Could not move by diagonal from F8 to E6"));
    }

    @Test
    public void whenSuccessfullyMove()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        logic.move(Cell.F8, Cell.C5);
    }
}