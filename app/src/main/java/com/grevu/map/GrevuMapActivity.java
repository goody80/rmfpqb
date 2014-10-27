package com.grevu.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.grevu.app.R;
import com.grevu.app.constant.GrevuContstants;
import com.grevu.app.util.Logger;
import com.grevu.category.ItemActivity;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrevuMapActivity extends Activity implements View.OnClickListener, MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapView.POIItemEventListener {
    MapView mMapView;
    MapPointBounds mMapPointBounds;
    Map<String,MapPointBounds> mPointBoundsMap;

    ViewGroup mapViewGroup;
    Button btnCancel;
    Button btnCompletePoi;

    List<MapPoint> mapPointList;


    boolean isInitial = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_grevu_map);
        mMapView = new MapView(this);
        mMapView.setDaumMapApiKey(GrevuContstants.DAUM_API_KEY);
        mMapView.setMapViewEventListener(this);
        mMapView.setCurrentLocationEventListener(this);

        mapViewGroup = (ViewGroup) findViewById(R.id.mapViewGroup);
        mapViewGroup.addView(mMapView);

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
        mv.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
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

        mv.setZoomLevel(GrevuContstants.ZOOM_LEVEL, true);

        isInitial = true;

    }

    @Override
    public void onMapViewLongPressed(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mv, MapPoint mp) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mv, MapPoint mp) {
        //if user tapped a point, then stop tracking mode
        mv.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);

        mapPointList.add(mp);

        //if user tapped at least 2 point, grevu displayed polyline.
        if (mapPointList.size() > 0) {
            MapPolyline existingPolyline = mMapView.findPolylineByTag(GrevuContstants.POLYLINE_TAG);

            if (existingPolyline != null) {
                mMapView.removePolyline(existingPolyline);
            }

            MapPolyline mapPolyline = new MapPolyline(mapPointList.size());
            mapPolyline.setTag(GrevuContstants.POLYLINE_TAG);
            mapPolyline.setLineColor(Color.argb(128, 255, 0, 0));

            mMapView.addPolyline(convertPointToLine(mapPolyline, mapPointList));

            if (mMapView.isShowingCurrentLocationMarker()) {

            }

            //remove all poi
            mMapView.removeAllPOIItems();

            //add new marker after remove current marker
            mMapView.addPOIItem(GrevuMapUtil.setCurrentPositionByMarker(mapPointList.get(mapPointList.size() - 1)));

            mMapView.moveCamera(CameraUpdateFactory.newMapPoint(mp, GrevuContstants.ZOOM_LEVEL));
        }
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mv, int currentZoomLevel) {
        Logger.d("[GrevuMapActivity] onMapViewZoomLevelChanged. current zoom level is " + currentZoomLevel);
    }

    @Override
    public void onCurrentLocationUpdate(MapView mv, MapPoint mp, float v) {
        Logger.i("[GrevuMapActivity] onCurrentLocationUpdate / current longitude =  " + mp.getMapPointGeoCoord().longitude + ", latitude = " + mp.getMapPointGeoCoord().latitude);

        MapCircle mapCircle = new MapCircle(MapPoint.mapPointWithGeoCoord(mp.getMapPointGeoCoord().latitude, mp.getMapPointGeoCoord().longitude),500, GrevuContstants.GREVU_CIRCLE.getCircleLineColor(), GrevuContstants.GREVU_CIRCLE.getCircleFillColor());
        mapCircle.setTag(GrevuContstants.CIRCLE_TAG);
        mv.addCircle(mapCircle);

        MapPointBounds[] mapPointBoundsArray = {mapCircle.getBound()};
        MapPointBounds mapPointBounds = new MapPointBounds(mapPointBoundsArray);

        if (mPointBoundsMap == null) {
            mPointBoundsMap = new HashMap<String, MapPointBounds>();
        }
        mPointBoundsMap.put("kCurMapPoint", mapPointBounds);

        int padding = 50;
        mv.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCancel:
                mMapView.removeAllPolylines();
                mMapView.removeAllPOIItems();
                mapPointList.clear();

                //move to user's current position
                MapPointBounds bounds = mPointBoundsMap.get("kCurMapPoint");

                mMapView.moveCamera(CameraUpdateFactory.newMapPoint(bounds.getCenter()));

                break;
            case R.id.btnComplete:
                //move to category activity
                Intent intent = new Intent(this, ItemActivity.class);

                startActivity(intent);
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
