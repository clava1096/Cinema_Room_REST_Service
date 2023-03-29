package cinema.entitys;

import cinema.repository.CinemaRepository;

import java.util.Collection;
import java.util.List;

public class Statistics {

    public static final String password = "super_secret";

    private int currentIncome;

    private int numOfSeats = CinemaRepository.total_columns * CinemaRepository.total_rows;

    private int numOfPurchasedSeats;

    public Statistics(int currentIncome, int numOfPurchasedSeats, Collection<Seat> pticket){
        this.currentIncome = currentIncome;
        this.numOfPurchasedSeats = numOfPurchasedSeats;
        this.numOfSeats-=pticket.size();
    }

    public int getCurrent_income() {
        return currentIncome;
    }

    public int getNumber_of_available_seats() {
        return numOfSeats;
    }

    public int getNumber_of_purchased_tickets() {
        return numOfPurchasedSeats;
    }
}
