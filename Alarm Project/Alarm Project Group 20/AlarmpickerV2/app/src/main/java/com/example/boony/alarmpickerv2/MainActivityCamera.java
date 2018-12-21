package com.example.boony.alarmpickerv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivityCamera extends Activity {

	private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
	private Button btnSelect;
	private ImageView ivImage;
	private String userChoosenTask;
    File destination;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maincamera);
		btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
		btnSelect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage();
			}
		});
		ivImage = (ImageView) findViewById(R.id.ivImage);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					if(userChoosenTask.equals("Take Photo"))
						cameraIntent();
					else if(userChoosenTask.equals("Choose from Library"))
						galleryIntent();
				} else {
					//code for deny
				}
				break;
		}
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityCamera.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				boolean result=Utility.checkPermission(MainActivityCamera.this);

				if (items[item].equals("Take Photo")) {
					userChoosenTask ="Take Photo";
					if(result)
						cameraIntent();

				} else if (items[item].equals("Choose from Library")) {
					userChoosenTask ="Choose from Library";
					if(result)
						galleryIntent();

				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	private void galleryIntent()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);//
		startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
	}

	private void cameraIntent()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQUEST_CAMERA);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_FILE)
				onSelectFromGalleryResult(data);
			else if (requestCode == REQUEST_CAMERA)
				onCaptureImageResult(data);
		}
	}

	private void onCaptureImageResult(Intent data) {
		Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

		destination = new File(Environment.getExternalStorageDirectory(),
				"picture1" + ".jpg");


		FileOutputStream fo;
		try {
			destination.createNewFile();
			fo = new FileOutputStream(destination);
			fo.write(bytes.toByteArray());
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		imageProcessing(destination);
	}

	@SuppressWarnings("deprecation")
	private void onSelectFromGalleryResult(Intent data) {

		Bitmap bm=null;
		if (data != null) {
			try {
				bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		ivImage.setImageBitmap(bm);
	}

	private void imageProcessing(File f1) {


		Bitmap bm1 = BitmapFactory.decodeFile(destination.getAbsolutePath(), new BitmapFactory.Options());
		//Bitmap bm2 = BitmapFactory.decodeFile(f2.getPath());
		Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.testimg);

		if(imagesAreEqual(bm1, bm3))  // use imagesAreEqual() mathod for check two iamge equal or not //

		{
			Toast.makeText(getApplicationContext(), "Two Image are Equal", Toast.LENGTH_LONG).show(); // or do anything else
		}

		else

		{
			Toast.makeText(getApplicationContext(), "Two Image are NOT Equal", Toast.LENGTH_LONG).show(); // or do anything else
		}
	}

	private boolean imagesAreEqual(Bitmap i1, Bitmap i2) {

		for (int y = 0; y < i1.getHeight(); ++y)
			for (int x = 0; x < i1.getWidth(); ++x)
				if (i1.getPixel(x, y) != i2.getPixel(x, y)) return false;

		return true;
	}


}
