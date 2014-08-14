package com.kic.hrm.shared;

import java.io.IOException;
import java.text.NumberFormat;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;


public class FileUploadProgressListener implements MediaHttpUploaderProgressListener{

	@SuppressWarnings("incomplete-switch")
	@Override
	public void progressChanged(MediaHttpUploader uploader) throws IOException {
		// TODO Auto-generated method stub
		 switch (uploader.getUploadState()) {
	      case INITIATION_STARTED:
	        Views.header2("Upload Initiation has started.");
	        break;
	      case INITIATION_COMPLETE:
	        Views.header2("Upload Initiation is Complete.");
	        break;
	      case MEDIA_IN_PROGRESS:
	        Views.header2("Upload is In Progress: "
	            + NumberFormat.getPercentInstance().format(uploader.getProgress()));
	        break;
	      case MEDIA_COMPLETE:
	        Views.header2("Upload is Complete!");
	        break;
	    }
	}

}
