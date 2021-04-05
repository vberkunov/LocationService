package ua.com.locationservice.location;

import android.os.Bundle;
import ua.com.locationservice.entity.Tag;

public interface LocationListener {

    void onLocationChanged(Tag tag);

    void onStatusChanged(String provider, int status, Bundle extras);

    void onProviderEnabled(String provider);

    void onProviderDisabled(String provider);
}
