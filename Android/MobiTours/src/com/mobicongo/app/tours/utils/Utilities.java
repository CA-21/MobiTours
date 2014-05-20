/**
 * 
 */
package com.mobicongo.app.tours.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * @author Mishka (misamuna@gmail.com)
 *
 */

public class Utilities {

	/*
	 * Convert from Bitmap to byte array
	 */
	public static byte[] getBytes(Bitmap bitmap){
		ByteArrayOutputStream stream= new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 0, stream);
		
		return stream.toByteArray();
	}
	
	/*
	 * Convert from byte array to bitmap
	 */
	public static Bitmap getImage(byte[] image){
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}
	
}
