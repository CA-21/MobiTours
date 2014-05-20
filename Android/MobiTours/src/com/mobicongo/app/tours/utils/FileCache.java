package com.mobicongo.app.tours.utils;

import java.io.File;
import android.content.Context;

	public class FileCache {
	
		private File cacheDir;
		
		public FileCache(Context context){
			//find the dir to save cached images
			if(android.os.Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED))
				cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"Mobitours_img");
			else
				cacheDir=context.getCacheDir();
				if(!cacheDir.exists())
					cacheDir.mkdirs();
		}
		
		public File getFile(String url){
			String filename=String.valueOf(url.hashCode());
			//u can do also this : String filename=URLEncoder.encode(url)
			File f=new File(cacheDir,filename);//
			return f;
		}
		
		public void clear(){
			File[] files=cacheDir.listFiles();
			if(files==null)
				return;
			for(File f:files)
				f.delete();
		}
		
		
		
		
		
		
		
//fin class		
	}
	 
