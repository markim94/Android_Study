package org.techtown.sampleappwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

public class MyLocationProvider extends AppWidgetProvider {
    static double xcoord;
    static double ycoord;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for(int i = 0; i < appWidgetIds.length; i++){
            int appWidgetId = appWidgetIds[i];

            String url = "geo:" + ycoord + "," + xcoord;
            String query = ycoord + "," + xcoord + "(내위치)";
            String encodedQuery = Uri.encode(query);
            String urlStr = url + "?q="+encodedQuery+"&z=15"; //확대 축소를 위한

            Uri uri = Uri.parse(urlStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,  0); // 대기하고 있다가 인텐트로 실행
            RemoteViews views = new RemoteViews(Context.getPackageName(), R.layout.mylocation);
            views.setOnClickPendingIntent(R.id.txtinfo, pendingIntent); // txtinfo를클릭하면 대기 인텐트 실행

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        context.startService(new Intent(context, GPSLocationService.class));
    }

    public static class GPSLocationService extends Service{
        LocationManager manager;


        @Override
        public void onCreate() {
            super.onCreate();
            manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startListening(); //위치정보 확인 변수 할당 코드

            return super.onStartCommand(intent, flags, startId);
        }

        public void startListening(){
            manager.requestLocationUpdates(LocationProvider,
                    10000,
                    0,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            ycoord = location.getLatitude();
                            xcoord = location.getLongitude();
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
        }
        public void stopListening(){

        }


        @Override
        public void onDestroy() {
            stopListening(); // 위치정보를 더이상 받지 않는.
            super.onDestroy();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
