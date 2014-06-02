package org.motechproject.hub.mds;

// Generated Apr 21, 2014 6:59:44 PM by Hibernate Tools 3.4.0.CR1


import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 * HubSubscriberTransaction generated by hbm2java
 */
@Entity
public class HubSubscriberTransaction implements java.io.Serializable {

	private static final long serialVersionUID = -2823908898058704053L;
	
	//TODO this should be an Integer ..not supported by mds
	@Field(required=true)
	private Integer hubDistributionStatusId;
	
	//TODO this should be an Integer ..not supported by mds
	@Field(required=true)
	private Integer hubSubscriptionId;
	
	//TODO this should be an Integer ..not supported by mds
	@Field(required=true)
	private Integer retryCount;
	
	@Field
	private String content;
	
	public Integer getHubDistributionStatusId() {
		return hubDistributionStatusId;
	}

	public void setHubDistributionStatusId(Integer hubDistributionStatusId) {
		this.hubDistributionStatusId = hubDistributionStatusId;
	}

	public Integer getHubSubscriptionId() {
		return hubSubscriptionId;
	}

	public void setHubSubscriptionId(Integer hubSubscriptionId) {
		this.hubSubscriptionId = hubSubscriptionId;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Field(required=true)
	private String contentType;

	public HubSubscriberTransaction() {
	}

	public HubSubscriberTransaction(
			Integer hubDistributionStatusId,
			Integer hubSubscriptionId, Integer retryCount, String contentType) {
		this.hubDistributionStatusId = hubDistributionStatusId;
		this.hubSubscriptionId = hubSubscriptionId;
		this.retryCount = retryCount;
		this.contentType = contentType;
	}

	public HubSubscriberTransaction(Integer hubDistributionStatusId,
			Integer hubSubscriptionId, Integer retryCount, String contentType,
			String content) {
		this.hubDistributionStatusId = hubDistributionStatusId;
		this.hubSubscriptionId = hubSubscriptionId;
		this.retryCount = retryCount;
		this.contentType = contentType;
		this.content = content;
	}

	

}