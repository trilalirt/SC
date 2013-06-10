package com.triladroid.spycamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	private CameraPreview mPreview;
	private Camera mCamera;
	
	// this is where we write our pic to destination file
	private PictureCallback mPicture = new PictureCallback() {

		private static final String TAG = "MyActivity";
		
		@Override
	    public void onPictureTaken(byte[] data, Camera camera) {

	        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
	        if (pictureFile == null){
	            Log.d(TAG, "Error creating media file, check storage permissions: " );
	            return;
	        }

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(data);
	            fos.close();
	        } catch (FileNotFoundException e) {
	            Log.d(TAG, "File not found: " + e.getMessage());
	        } catch (IOException e) {
	            Log.d(TAG, "Error accessing file: " + e.getMessage());
	        }
	    }
	};
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
               
        // is there is a cam
        checkCameraHardware(getApplicationContext());
        
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
        getWindow().setAttributes(params);
       
    }
	
		
	@Override
    protected void onResume() {
        super.onResume();
        
                
        mCamera = getCameraInstance();
        
        CameraPreview mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.removeAllViews();
        preview.addView(mPreview);     
      
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{ 
	   if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) { 
	       
		   mCamera.takePicture(null, null, mPicture);
		   
	       return true;
	   } else {
	       return super.onKeyDown(keyCode, event); 
	   }
	}
	
	 /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
	
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
            Camera.Parameters parameters = c.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
	
//	/** Create a file Uri for saving an image  */
//	private static Uri getOutputMediaFileUri(int type){
//		
//	      return Uri.fromFile(getOutputMediaFile(type));
//	}

	/** Create a File for saving an image */
	private static File getOutputMediaFile(int type){
	    
		// To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SpyCamera");
		//File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "SpyCamera");
		
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.
	    // Create the storage directory if it does not exist
	    
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("SpyCamera", "failed to create directory");
	            Log.i("SpyCamera", "failed to create directory");
	            return null;
	        }
	    }
	    
	 // Create a media file name
	    Calendar c = Calendar.getInstance(); 
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String timeStamp =  df.format(c.getTime());
	    
	    File mediaFile;
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
	   
	    return mediaFile;
	}
	
	
	@Override
    protected void onPause() {
        super.onPause();
        releaseCamera();              
    }
	
	private void releaseCamera(){
        if (mCamera != null){
        	
        	mCamera.stopPreview(); 
        	//mCamera.setPreviewCallback(null);
        	//mPreview.getHolder().removeCallback(mPreview);
        	mCamera.lock();
           	mCamera.release();        // release the camera
            mCamera = null;
        }
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
 //       return true;
  //  }
    
}
