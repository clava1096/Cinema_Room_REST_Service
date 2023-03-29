package cinema.controller;

import cinema.entitys.Cinema;
import cinema.entitys.Statistics;
import cinema.entitys.Token;
import cinema.repository.CinemaRepository;
import cinema.entitys.Seat;
import cinema.services.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class CinemaController {
    private CinemaRepository cinemaRepository = new CinemaRepository();

    private CinemaService cinemaService = new CinemaService();

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinemaService.getCinemaInfo();
    }


    @GetMapping("/available_seats/{price}")
    public Seat getPrice(@PathVariable int price){
        for(Seat c : cinemaRepository.getAvailable_seats()){
            if(c.getPrice() == price) return c;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/purchase")
    public Map<String, Object> purchaseTicket(@RequestBody Seat seat){
        return cinemaService.purchaseTicket(seat);
    }

    @PostMapping(path = "/return")
    public Map<String, Seat> returnTicket(@RequestBody Token token){

        return cinemaService.returnTicket(token);
    }

    @PostMapping(path = "/stats")
    public Statistics getStats(@RequestParam(required = false) String password){
        return cinemaService.getStats(password);
    }

}
