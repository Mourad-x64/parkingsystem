package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    private double MILLISECONDS_IN_SECOND = 1000f;
    private double SECONDS_IN_HOUR = 3600f;


    public void calculateFare(Ticket ticket, int discount){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //bug le code actuelle récupère uniquement l'heure de départ et d'arrivée
        //ce qui donne toujours 0 en desous d'une heure.
        //on veut plutot récupérer le timestamp des deux horaires et prendre la différence.

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        //TODO DONE: Some tests are failing here. Need to check if this logic is correct
        //duration est en heures donc on divise par 1000 puis 3600
        //ne pas oublier le f pour la précision du float
        double duration = ((outHour - inHour)/ MILLISECONDS_IN_SECOND)/SECONDS_IN_HOUR;

        //on arrondi à 3 chiffres après la virgule
        duration = Math.round(duration * 1000.0) / 1000.0;

        //promo moins de 30 minutes
        if(duration <= 0.50){
            duration = 0;
        }

        //on ajoute le discount au prix
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                double price = duration * Fare.CAR_RATE_PER_HOUR;
                ticket.setPrice(calculateDiscount(discount, price));
                break;
            }
            case BIKE: {
                double price = duration * Fare.BIKE_RATE_PER_HOUR;
                ticket.setPrice(calculateDiscount(discount, price));
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }

    //fonction pour calculer le discount
    public double calculateDiscount(int discount, double price){
        double discountedPrice = price - ((price * discount)/100);
        //on arrondi à 3 chiffres après la virgule
        discountedPrice = Math.round(discountedPrice * 1000.0) / 1000.0;

        return discountedPrice;
    }
}