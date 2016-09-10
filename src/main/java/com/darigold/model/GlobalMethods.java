package com.darigold.model;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GlobalMethods {



    //Create Hibernate Session method
    public static final SessionFactory ourSessionFactory;

    static {
        try {
            //For hibernate 5+, using ServiceRegistry causes configuration.configure() mapping to be lost, and throws error for class not
            //mapped. Use below to create session factory without ServiceRegistry
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }



    //query needed columns from database and store in list
    @SuppressWarnings("unchecked")
    public static List<CsvDataEntity> baseQuery() throws HibernateException{
        Session session = getSession();
        session.beginTransaction();
        String hql = "SELECT CsvDataEntity.clearanceMainCat, CsvDataEntity.clearanceDT, CsvDataEntity.vertRadians, CsvDataEntity.latitude FROM CsvDataEntity";
        List<CsvDataEntity> list = session.createQuery(hql).list();
        session.close();
        System.out.print(list.toString());
        return list;
    }

    //TODO add in functionality to handle the condition when nothing is returned based on user-defined parameters
    //pass in the query return list
    public static Map<Integer, String> analyzeQuery(String address, String distance, String years, List<CsvDataEntity> fullList) throws Exception{

        //create 'context' for GoogleMaps GeoCode API, geocode user-defined address
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyB3HBeFw_I35oOAnOcpjxctSp5wFx2-wqE");
        GeocodingResult[] result = GeocodingApi.geocode(context, address).await();
        Geometry coordinates = result[0].geometry;

        //store user request lat & lng from address supplied from form
        double longitude = coordinates.location.lng;
        double latitude = coordinates.location.lat;

        //user requested distance, converted from miles to meters
        double requestedDistanceInMiles = Double.parseDouble(distance);

        //list to store all the crimes within the specified distance
        List<String> crimeList = new ArrayList<>();

        for (CsvDataEntity record : fullList) {

            //verifying crime was commmitted within user-defined distance
            Boolean withinDistance = false;
            double rowLng = Double.parseDouble(record.getVertRadians());
            double rowLat = Double.parseDouble(record.getLatitude());
            double milesBetween = (haversineDistance(latitude,rowLat,longitude,rowLng,0,0)) / 1609.34;

            if (milesBetween <= requestedDistanceInMiles) {
                withinDistance = true;
            }

            //verifying crime was committed within user-defined time-range
            Boolean withinTimeRange =false;
            double requestedYears = Double.parseDouble(years);
            long requestedDays = Math.round(requestedYears * 365);
            String recordDateAsString = record.getClearanceDt().substring(0, 9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);

            //compare two dates
            LocalDate recordDateMaster = LocalDate.parse(recordDateAsString,formatter);
            LocalDate currentDate = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(currentDate, recordDateMaster);

            if (daysBetween <= requestedDays) {
                withinTimeRange = true;
            }

            if (withinDistance && withinTimeRange) {
                crimeList.add(record.getClearanceMainCat());
            }
        }
    }


    //haversine formula for distance between coordinates
    public static double haversineDistance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
