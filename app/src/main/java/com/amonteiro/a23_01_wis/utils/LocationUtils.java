package com.amonteiro.a23_01_wis.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.annotation.RequiresPermission;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LocationUtils {

    //retourne la dernière position connue
    public static Location getLastKnownLocation(Context c) {

        //Contrôle de la permission
        if (ContextCompat.checkSelfPermission(c, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            return null;
        }

        LocationManager lm = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
        Location bestLocation = null;
        //on teste chaque provider(réseau, GPS...) et on garde celui avec la meilleurs précision
        for (String provider : lm.getProviders(true)) {
            Location l = lm.getLastKnownLocation(provider);
            if (l != null && (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy())) {
                bestLocation = l;
            }
        }

        return bestLocation;
    }
}
