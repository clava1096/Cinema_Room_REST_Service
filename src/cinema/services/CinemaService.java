package cinema.services;


import cinema.entitys.Cinema;
import cinema.entitys.Statistics;
import cinema.entitys.Token;
import cinema.exceptions.SeatOutOfBoundException;
import cinema.exceptions.TicketAlreadyPurchasedException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTockenException;
import cinema.repository.CinemaRepository;
import cinema.entitys.Seat;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Service
public class CinemaService {
    private CinemaRepository cinemaRepository = new CinemaRepository();


    public Cinema getCinemaInfo(){
        return new Cinema(CinemaRepository.total_rows, CinemaRepository.total_columns, cinemaRepository.getAvailable_seats());
    }

    public Map<String, Object> purchaseTicket(Seat avaibleSeat) {
        if(!seatIsFree(avaibleSeat)) throw new SeatOutOfBoundException();//return Map.of("error", "The number of a row or a column is out of bounds!"); //throw new SeatOutOfBoundException();
        for(Seat c : cinemaRepository.getPurchased_tickets().values()){
            if(avaibleSeat.equals(c)) throw new TicketAlreadyPurchasedException();//return Map.of("error", "The ticket has been already purchased!");//throw new TicketAlreadyPurchasedException();
        }
        return cinemaRepository.saveTicket(new Token().getToken(), avaibleSeat);
    }


    public boolean seatIsFree(Seat seat){
        return seat.getRow() <= CinemaRepository.total_rows
                && seat.getRow() > 0
                && seat.getColumn() <= CinemaRepository.total_columns
                && seat.getColumn() > 0;
    }

    public Statistics getStats(String password){
        int curr = 0;
        int num = cinemaRepository.getPurchased_tickets().size(); // num of purchased tickets
        for(Map.Entry<String, Seat> en : cinemaRepository.getPurchased_tickets().entrySet()){
            curr+=en.getValue().getRow() < 5 ? 10 :8 ;
        }
        if(password == null) throw new WrongPasswordException();
        return new Statistics(curr, num, cinemaRepository.getPurchased_tickets().values());
    }

    public Map<String, Seat> returnTicket(Token token){
        if(cinemaRepository.containSeat(token.getToken())){
            Seat returnedSeat = cinemaRepository.getPurchased_tickets().get(token.getToken());
            cinemaRepository.getPurchased_tickets().remove(token.getToken());
            return Map.of("returned_ticket", returnedSeat);
        }
        else throw new WrongTockenException(); //Map.of("error", "Wrong token!");
    }
}
