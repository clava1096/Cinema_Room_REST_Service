package cinema.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Seat {
    private int row, column, price;

    public Seat(int row, int column){
        setRow(row);
        setColumn(column);
    }

    public void setRow(int row) {
        this.price = row < 5 ? 10 : 8;
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getPrice(){
        return price;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString(){
        return "row: " + row + " column: " + column + " price: " + price;
    }

    public boolean equals(@NotNull Seat seat){
        return this.column == seat.column && this.row == seat.row; //
    }
}
