package com.grevu.map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.grevu.app.R;
import com.grevu.app.constant.GrevuContstants;
import com.grevu.app.util.Logger;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class GrevuMapActivity extends Activity implements View.OnClickListener, MapView.MapViewEventListener {
    MapView mapView;
    ViewGroup mapViewGroup;
    Button btnCancel;
    Button btnCompletePoi;

    List<MapPoint> mapPointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_grevu_map);
        mapView = new MapView(this);
        mapView.setDaumMapApiKey(GrevuContstants.DAUM_API_KEY);
        mapView.setMapViewEventListener(this);

        mapViewGroup = (ViewGroup) findViewById(R.id.mapViewGroup);
        mapViewGroup.addView(mapView);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

		btnCompletePoi = (Button) findViewById(R.id.btnComplete);
		btnCompletePoi.setOnClickListener(this);

        mapPointList = new ArrayList<MapPoint>();
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewDragEnded(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewDragStarted(MapView mv, MapPoint mp) {

    }

    @SuppressWarnings("static-access")
    @Override
    public void onMapViewInitialized(MapView mv) {
        Logger.d("onMapViewInitialized");

        mv.setMapTilePersistentCacheEnabled(true);	//Use Map Cache
        mv.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        Logger.i("[GrevuMapActivity] current zoom level : " + mv.getZoomLevel());
        mv.setZoomLevel(GrevuContstants.ZOOM_LEVEL, true);
    }

    @Override
    public void onMapViewLongPressed(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mv, MapPoint mp) {
        Logger.d("[GrevuMapActivity] onMapViewMoveFinished");
    }

    @Override
    public void onMapViewSingleTapped(MapView mv, MapPoint mp) {
        mapPointList.add(mp);

        //if user tapped at least 2 point, grevu displayed polyline.
        if (mapPointList.size() > 1) {
            MapPolyline existingPolyline = mapView.findPolylineByTag(GrevuContstants.POLYLINE_TAG);

            if (existingPolyline != null) {
                mapView.removePolyline(existingPolyline);
            }

            MapPolyline mapPolyline = new MapPolyline(mapPointList.size());
            mapPolyline.setTag(GrevuContstants.POLYLINE_TAG);
            mapPolyline.setLineColor(Color.argb(255, 0, 0, 255));

            mapView.addPolyline(convertPointToLine(mapPolyline, mapPointList));
            
            int padding = 500;

            mapView.moveCamera(CameraUpdateFactory.newMapPoint(mp, GrevuContstants.ZOOM_LEVEL));
        }
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mv, int currentZoomLevel) {
        Logger.d("[GrevuMapActivity] onMapViewZoomLevelChanged. current zoom level is " + currentZoomLevel);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCancel:
                mapView.removeAllPolylines();
                mapPointList.clear();
                break;
            case R.id.btnComplete:

			    break;

        }
    }

    /**
     * Convert GeoCoord to MapPoint
     * */
    public MapPolyline convertPointToLine(MapPolyline mapPolyline, List<MapPoint> mapPointList) {
        MapPolyline polyline = null;
        if (mapPolyline == null) {
            polyline = new MapPolyline();
        } else {
            polyline = mapPolyline;
        }

        MapPoint[] arrMapPoint = new MapPoint[mapPointList.size()];

        for(int i=0; i < arrMapPoint.length; i++) {
            arrMapPoint[i] = mapPointList.get(i);
        }
        //polyline.addPoints((MapPoint[])mapPointList.toArray());
        polyline.addPoints(arrMapPoint);

        return polyline;
    }
}
