package org.techtown.samplecapture;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    Camera camera;
    
    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    // xml, java 두 생성방법이 있으므로 컨스트럭트를 2개 만듬, init으로 초기화


    private void init(Context context) {
        holder = getHolder(); //서페이스 홀더 참조
        holder.addCallback(this); // 뷰와 다르게 별도의 콜백메시지를 등록해주어야 함. implement 설정 해줌.
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = camera.open(); //카메라 객체 참조

        try {
            camera.setPreviewDisplay(holder); // 서페이스 뷰에 홀더 설정
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //화면에 보여주기 전 크기가 결정되는 시점
        camera.startPreview(); //미리보기 화면에 픽셀을 뿌린다.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview(); //미리보기 중지
        camera.release(); //리소스 해제
        camera = null; //완전 초기화
    }

    // 서페이스뷰 미리보기는 설정완료, 사진을 찍는 것을 이후 설정해주는 파트
    public boolean capture(Camera.PictureCallback callback) {
        if(camera !=null){
            camera.takePicture(null, null, callback); //사진찍기
            return true;
        }else{
            return false;
        }
    }


}
