package com.kic.hrm.shared;

import java.io.IOException;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpDownloaderProgressListener;


public class FileDownloadProgressListener implements MediaHttpDownloaderProgressListener{

	@Override
	public void progressChanged(MediaHttpDownloader downloader)
			throws IOException {
		// TODO Auto-generated method stub
		 switch (downloader.getDownloadState()) {
	      case MEDIA_IN_PROGRESS:
	        Views.header2("Download is in progress: " + downloader.getProgress());
	        break;
	      case MEDIA_COMPLETE:
	        Views.header2("Download is Complete!");
	        break;
	    }
	}

}
