package com.ceco.android.yagajobs.app.model;

import java.util.Date;
import java.util.Set;

/**
 * Created by ceco on 30.05.14.
 */
public class Vacancy {

   /**
	 * Vacancy id
	 */
	private Long id;

	/**
	 * Vacancy reference.
	 */
	private String reference;

	/**
	 * Unique id identifying this vacancy on its respective job board. Usually
	 * part of the url.
	 */
	private String jobId;

	/**
	 * Vacancy url
	 */
	private String url;

	/**
	 * The job title name, raw text.
	 */
	private String title;

    /**
	 * The job title name, raw text.
	 */
	private String jobBoard;

	/**
	 * Date this vacancy was posted on the jobboard.
	 */
	private Date datePosted;

//	/**
//	 * Date this vacancy was mined from the jobboard.
//	 */
//	private Date dateFound;
//
//	/**
//	 * Date vacancy was updated/last seen.
//	 */
//	private Date dateUpdated;
//
//	/**
//	 * Expiration date of posting, as given on website or as calculated by us.
//	 */
//	private Date dateExpiry;
//
//	/**
//	 * Actual date posting was deleted from the database, i.e. a posting is
//	 * deleted if dateDeleted is not null
//	 */
//	private Date dateDeleted;


	/**
	 * Primary location URI.
	 */
	private String location;

	/**
	 * Salary for this vacancy, raw text.
	 */
	private String salary;

	/**
	 * Minimum value for salary, derived from raw salary.
	 */
	private Integer salaryMinYearly;

	/**
	 * Maximum value for salary, derived from raw salary.
	 */
	private Integer salaryMaxYearly;

	/**
	 * The position type for this vacancy (e.g. "Contract"), raw text.
	 */
	private String type;

	/**
	 * The working hours for this vacancy, e.g. "Fulltime" or "37.5/week"
	 */
	private String hours;

	/**
	 * Vacancy description, raw text.
	 */
	private String description;

	/**
	 * A set of sector names (raw text) for this vacancy.
	 */
	private Set<String> sectors;

    private String advertiserName;

    public Vacancy(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJobBoard() {
        return jobBoard;
    }

    public void setJobBoard(String jobBoard) {
        this.jobBoard = jobBoard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Date getDatePosted() {
//        return datePosted;
//    }
//
//    public void setDatePosted(Date datePosted) {
//        this.datePosted = datePosted;
//    }
//
//    public Date getDateFound() {
//        return dateFound;
//    }
//
//    public void setDateFound(Date dateFound) {
//        this.dateFound = dateFound;
//    }
//
//    public Date getDateUpdated() {
//        return dateUpdated;
//    }
//
//    public void setDateUpdated(Date dateUpdated) {
//        this.dateUpdated = dateUpdated;
//    }
//
//    public Date getDateExpiry() {
//        return dateExpiry;
//    }
//
//    public void setDateExpiry(Date dateExpiry) {
//        this.dateExpiry = dateExpiry;
//    }
//
//    public Date getDateDeleted() {
//        return dateDeleted;
//    }
//
//    public void setDateDeleted(Date dateDeleted) {
//        this.dateDeleted = dateDeleted;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getSalaryMinYearly() {
        return salaryMinYearly;
    }

    public void setSalaryMinYearly(Integer salaryMinYearly) {
        this.salaryMinYearly = salaryMinYearly;
    }

    public Integer getSalaryMaxYearly() {
        return salaryMaxYearly;
    }

    public void setSalaryMaxYearly(Integer salaryMaxYearly) {
        this.salaryMaxYearly = salaryMaxYearly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getSectors() {
        return sectors;
    }

    public void setSectors(Set<String> sectors) {
        this.sectors = sectors;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }
}
