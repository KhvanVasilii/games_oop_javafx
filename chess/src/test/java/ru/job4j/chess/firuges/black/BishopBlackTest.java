package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    public void whenCorrectPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        assertThat(bishopBlack.position()).isEqualTo(Cell.C8);
    }

    @Test
    public void whenCorrectCopy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Figure copy = bishopBlack.copy(Cell.F8);
        assertThat(copy.position()).isEqualTo(Cell.F8);
    }

    @Test
    public void whenG5ThenD2E3F4G5() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] way = bishopBlack.way(Cell.G5);
        assertThat(way).isEqualTo(new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5});
    }

    @Test
    public void whenException() {
        Cell position = Cell.C1;
        Cell dest = Cell.F3;
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    new BishopBlack(position).way(dest);
                });
        assertThat(exception.getMessage()).isEqualTo(
                "Could not move by diagonal from %s to %s", position, dest);
    }
}