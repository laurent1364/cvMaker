package mirage.springframework.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Mirage on 16/12/2016.
 */
@Entity
@Table(name = "experiences")
public class Experience  implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    @OneToOne
    private UserDetails userDetails;

    @NotNull
    private String title;

    @NotNull
    private String company;
    private String urlCompany;
    private String location;

    @NotNull
    private Date startDate;
    private Date endDate;
    @NotNull
    private String responsibilities;
    private String achievement1;
    private String achievement2;
    private String achievement3;

    public Experience() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrlCompany() {
        return urlCompany;
    }

    public void setUrlCompany(String urlCompany) {
        this.urlCompany = urlCompany;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getAchievement1() {
        return achievement1;
    }

    public void setAchievement1(String achievement1) {
        this.achievement1 = achievement1;
    }

    public String getAchievement2() {
        return achievement2;
    }

    public void setAchievement2(String achievement2) {
        this.achievement2 = achievement2;
    }

    public String getAchievement3() {
        return achievement3;
    }

    public void setAchievement3(String achievement3) {
        this.achievement3 = achievement3;
    }
}

