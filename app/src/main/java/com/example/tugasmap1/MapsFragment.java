package com.example.tugasmap1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inisialisasi View
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        //Insialisasi Maps Fragment
        SupportMapFragment supportMapFragment =(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_maps);
        //Async Maps
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                // Ketika Maps Loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        //ketika klik dalam maps
                        //insialisi marker option
                        MarkerOptions markerOptions = new MarkerOptions();
                        //atur posisi marker
                        markerOptions.position(latLng);
                        //set title marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        //hapus semua marker
                        googleMap.clear();
                        //Animasi zoom
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        //Menambahkan marker pada maps
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;
    }
}