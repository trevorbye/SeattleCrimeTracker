package com.darigold.model;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public static List<Object[]> baseQuery() throws HibernateException{
        Session session = getSession();
        Criteria criteria = session.createCriteria(CsvDataEntity.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("clearanceMainCat"));
        projectionList.add(Projections.property("clearanceDt"));
        projectionList.add(Projections.property("vertRadians"));
        projectionList.add(Projections.property("latitude"));
        criteria.setProjection(projectionList);

        List<Object[]> list = criteria.list();
        return list;
    }


    //method to loop through location-filtered crime list and perform distinct count
    public static Map<String, Integer> distinctCountMap(List<CrimeModel> crimeList) {

        Map<String, Integer> distinctMap = new HashMap<>();
        String crime;

        for (CrimeModel record : crimeList) {
            crime = record.getMainCrimeDesc();

            if (distinctMap.containsKey(crime)) {
                distinctMap.put(crime, distinctMap.get(crime) + 1);
            } else {
                distinctMap.put(crime, 1);
            }
        }

        return distinctMap;
    }

    //transfers distinctCountMap into a reversed navigable map
    public static NavigableMap<Integer,String> rankedMap(Map<String,Integer> distinctMap) {
        TreeMap<Integer,String> treeMap = new TreeMap<>();

        for (Map.Entry<String,Integer> entry : distinctMap.entrySet()) {
            String valueTemp = entry.getKey();
            Integer keyTemp = entry.getValue();
            treeMap.put(keyTemp,valueTemp);
        }

        NavigableMap<Integer,String> nmap = treeMap.descendingMap();
        return nmap;
    }

    //convert navigable list into list of CrimeRank objects to use in model
    public static List<CrimeRank> distinctAsList(NavigableMap<Integer,String> navigableMap) {
        List<CrimeRank> list = new ArrayList<>();

        for (Map.Entry<Integer,String> entry : navigableMap.entrySet()) {
            CrimeRank rankObject = new CrimeRank(entry.getValue(),entry.getKey());
            list.add(rankObject);
        }

        return list;
    }


    //pass in the query return list
    public static List<CrimeModel> analyzeQuery(String address, String distance, String years, List<Object[]> fullList) throws Exception{

        //create 'context' for GoogleMaps GeoCode API, geocode user-defined address
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyB3HBeFw_I35oOAnOcpjxctSp5wFx2-wqE");
        GeocodingResult[] result = GeocodingApi.geocode(context, address).await();
        Geometry coordinates = result[0].geometry;

        //store user request lat & lng from address supplied from form
        double longitude = coordinates.location.lng;
        double latitude = coordinates.location.lat;

        //user requested distance
        double requestedDistanceInMiles = Double.parseDouble(distance);

        //list to store all the crimes within the specified distance
        List<CrimeModel> crimeList = new ArrayList<>();

        for (Object[] record : fullList) {

            //verifying crime was commmitted within user-defined distance
            Boolean withinDistance = false;
            String recordLongtiude = (String)record[2];
            String recordLatitude = (String)record[3];
            double milesBetween;

            if (recordLongtiude.isEmpty() || recordLatitude.isEmpty()) {
                continue;
            } else {
                double rowLng = Double.parseDouble((String) record[2]);
                double rowLat = Double.parseDouble((String) record[3]);
                milesBetween = (haversineDistance(latitude, rowLat, longitude, rowLng, 0, 0)) / 1609.34;
            }

            if (milesBetween <= requestedDistanceInMiles) {
                withinDistance = true;
            }

            //verifying crime was committed within user-defined time-range
            Boolean withinTimeRange =false;
            double requestedYears = Double.parseDouble(years);
            long requestedDays = Math.round(requestedYears * 365);
            String objectDate = (String)record[1];
            String recordDateAsString;

                //skip null records in database
                if (objectDate.length() < 10) {
                    continue;
                } else {
                    recordDateAsString = objectDate.substring(0, 10);
                }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);

            //compare two dates
            LocalDate recordDateMaster = LocalDate.parse(recordDateAsString,formatter);
            LocalDate currentDate = LocalDate.now();
            long daysBetween = Math.abs(ChronoUnit.DAYS.between(currentDate, recordDateMaster));

            if (daysBetween <= requestedDays) {
                withinTimeRange = true;
            }

            String crimeDesc = (String)record[0];
            if (crimeDesc.isEmpty()) {
                continue;
            }

            if (withinDistance && withinTimeRange) {
                crimeList.add(new CrimeModel(crimeDesc, milesBetween, recordDateMaster));
            }
        }

        return crimeList;
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
