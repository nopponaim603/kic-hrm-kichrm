package com.kic.hrm.data.model;

import java.util.Date;

public class FileIDStartTimeLog {
	public enum property {
		fileId,
		lastUpdate
	}
	//@Attribute(primaryKey = true)
    //@Sync(true)
    private String Kind;

    //@Attribute(version = true)
    private Long keyID;
    
    private String fileId;
    
    private Date lastUpdate;

	/**
	 * @return the kind
	 */
	public String getKind() {
		return Kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		Kind = kind;
	}

	/**
	 * @return the keyID
	 */
	public Long getKeyID() {
		return keyID;
	}

	/**
	 * @param keyID the keyID to set
	 */
	public void setKeyID(Long keyID) {
		this.keyID = keyID;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


}
