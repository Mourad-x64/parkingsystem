package com.parkit.parkingsystem.constants;

public class DBConstants {

    public static final String GET_NEXT_PARKING_SPOT = "select min(PARKING_NUMBER) from parking where AVAILABLE = true and TYPE = ?";
    public static final String UPDATE_PARKING_SPOT = "update parking set available = ? where PARKING_NUMBER = ?";

    public static final String SAVE_TICKET = "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME) values(?,?,?,?,?)";
    public static final String UPDATE_TICKET = "update ticket set PRICE=?, OUT_TIME=? where ID=?";
    /*
        bug dans la requete car elle renvoie le premier tiket de la plaque d'immatriculation
        au lieu du dernier en date, il faut lui ajouter DESC
    *
    * */
    public static final String GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME DESC  limit 1";

    //requete qui renvoi le nombre de fois qu'une plaque d'immatriculation est entrée dans le parking
    //pour le discount 5%
    public static final String GET_USER_VISITS = "select COUNT(t.ID) AS VISITS from ticket t where t.VEHICLE_REG_NUMBER=?";

    public static final String CHECK_TICKET = "select COUNT(t.ID) from ticket t where t.VEHICLE_REG_NUMBER=? and ISNULL(t.OUT_TIME) = 1";
}
