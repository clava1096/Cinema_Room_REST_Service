package cinema.repository;


import cinema.entitys.Seat;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CinemaRepository {
    public static final int total_rows = 9;
    public static final int total_columns = 9;
    private static final List<Seat> available_seats = new ArrayList<>();

    private static final Map<String, Seat> purchased_tickets;

    static {
        purchased_tickets = new HashMap<>();
        for(int i = 1; i <= total_rows; i++){
            for(int j = 1; j <= total_columns; j++){
                Seat SEAT = new Seat(i,j);
                available_seats.add(SEAT);
            }
        }
    }


    public boolean containSeat(@NotNull String token){
        return purchased_tickets.containsKey(token);
    }

    public Map<String, Object> saveTicket(String token, Seat seat){
        purchased_tickets.put(token, seat);
        return Map.of("token", token, "ticket",seat);
    }

    public Map<String, Seat> getPurchased_tickets(){
        return purchased_tickets;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }
}