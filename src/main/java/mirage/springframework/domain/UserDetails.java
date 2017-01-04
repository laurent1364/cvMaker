package mirage.springframework.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Entity
@Table(name = "user_details")
public class UserDetails  implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    @OneToOne(cascade =  CascadeType.PERSIST)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", orphanRemoval = true)
    private List<Domain> domains = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", orphanRemoval = true)
    private List<Referee> referees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", orphanRemoval = true)
    private List<Curriculum> curriculums = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public List<Curriculum> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }
}
