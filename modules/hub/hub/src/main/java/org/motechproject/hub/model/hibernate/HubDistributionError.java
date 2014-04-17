package org.motechproject.hub.model.hibernate;

// Generated Apr 11, 2014 3:15:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HubDistributionError generated by hbm2java
 */
@Entity
@Table(name = "hub_distribution_error", schema = "hub")
public class HubDistributionError implements java.io.Serializable {

	private Long distributionErrorId;
	private HubSubscription hubSubscription;
	private String errorDiscription;
	private Date createTime;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;

	public HubDistributionError() {
	}

	public HubDistributionError(Long distributionErrorId,
			HubSubscription hubSubscription) {
		this.distributionErrorId = distributionErrorId;
		this.hubSubscription = hubSubscription;
	}

	public HubDistributionError(Long distributionErrorId,
			HubSubscription hubSubscription, String errorDiscription,
			Date createTime, Date lastUpdated, String createdBy,
			String lastUpdatedBy) {
		this.distributionErrorId = distributionErrorId;
		this.hubSubscription = hubSubscription;
		this.errorDiscription = errorDiscription;
		this.createTime = createTime;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Id
	@Column(name = "distribution_error_id", unique = true, nullable = false)
	public Long getDistributionErrorId() {
		return this.distributionErrorId;
	}

	public void setDistributionErrorId(Long distributionErrorId) {
		this.distributionErrorId = distributionErrorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id", nullable = false)
	public HubSubscription getHubSubscription() {
		return this.hubSubscription;
	}

	public void setHubSubscription(HubSubscription hubSubscription) {
		this.hubSubscription = hubSubscription;
	}

	@Column(name = "error_discription", length = 150)
	public String getErrorDiscription() {
		return this.errorDiscription;
	}

	public void setErrorDiscription(String errorDiscription) {
		this.errorDiscription = errorDiscription;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "create_time", length = 15)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "last_updated", length = 15)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "created_by", length = 15)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "last_updated_by", length = 15)
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}