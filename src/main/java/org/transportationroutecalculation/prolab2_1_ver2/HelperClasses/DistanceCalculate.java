package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;

public interface DistanceCalculate {
    double calculateDistance(Point2D.Double source, Point2D.Double destination);
}

@Service("googleMaps")
class GoogleMapsDistanceCalculator implements DistanceCalculate {
    private final String apiKey;

    public GoogleMapsDistanceCalculator(@Value("${API_KEY}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public double calculateDistance(Point2D.Double source, Point2D.Double destination) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                    .origins(new com.google.maps.model.LatLng(source.getX(), source.getY()))
                    .destinations(new com.google.maps.model.LatLng(destination.getX(), destination.getY()))
                    .mode(com.google.maps.model.TravelMode.WALKING)
                    .await();
            return matrix.rows[0].elements[0].distance.inMeters / 1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error calculating distance", e);
        }
    }
}