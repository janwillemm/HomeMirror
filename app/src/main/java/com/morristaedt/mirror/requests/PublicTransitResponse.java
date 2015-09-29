package com.morristaedt.mirror.requests;

/**
 * Created by jw on 29/09/15.
 */
public class PublicTransitResponse {

    public RoutesResponse[] routes;

    private class RoutesResponse{
        public String copyrights;
        public LegsResponse[] legs;

        public String toString(){
            String result = "Route[" + copyrights + ", Legs{";
            for(int i = 0; i < legs.length; i++){
                result += legs[i].toString() + ", ";
            }
            return result.substring(0, result.length()-2) + "}]";
        }


    }

    private class LegsResponse{
        public DepartureTimeResponse departure_time;
        public DistanceResponse distance;

        public String toString(){
            return "Leg[" + departure_time.toString() + ", " + distance.toString() + "]";
        }
    }

    private class DepartureTimeResponse {
        public String text;
        public long value;

        public String toString(){
            return "Departuretime[" + text + ", " + value + "]";
        }
    }

    private class DistanceResponse {
        public String text;
        public long value;

        public String toString(){
            return "Distance[" + text + ", " + value + "]";
        }
    }

    public String getDuration(){
        return this.routes[0].legs[0].distance.text;
    }

    public String getDepartureTime(){
        return this.routes[0].legs[0].departure_time.text;
    }

    public String toString(){
        String result = "Response[{";
        for(int i = 0; i < routes.length; i++){
            result += routes[i].toString() + ", ";
        }
        return result.substring(0, result.length()-2) + "}]";
    }
}
