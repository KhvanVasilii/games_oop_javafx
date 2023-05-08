package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        PawnBlack pawnBlack = new PawnBlack(Cell.B7);
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class, () -> {
                    logic.move(Cell.C8, Cell.A6);
                });
        assertThat(exception).isInstanceOf(OccupiedCellException.class);
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class, () -> {
                    logic.move(Cell.C8, Cell.C5);
                });
        assertThat(exception).isInstanceOf(ImpossibleMoveException.class);
    }
}