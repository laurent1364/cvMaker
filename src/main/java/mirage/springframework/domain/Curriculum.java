package mirage.springframework.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * Created by Mirage on 17/12/2016.
 */
@Entity
@Table(name = "curriculum")
public class Curriculum implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    private Date htmlGenerated;
    private Date pdfGenerated;

    @OneToOne
    private UserDetails userDetails;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String interests;

    private String seekJob;

    private String htmlPreview;

    @ManyToMany
    @JoinTable(name = "cv_experience", joinColumns = @JoinColumn(name = "curriculum_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "experience_id", referencedColumnName = "id"))
    private List<Experience> experiences;

    @ManyToMany
    @JoinTable(name = "cv_education",  joinColumns = @JoinColumn(name = "curriculum_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "education_id", referencedColumnName = "id"))
    private List<Education> educations;

    @ManyToMany
    @JoinTable(name = "cv_domain",  joinColumns = @JoinColumn(name = "curriculum_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "domain_id", referencedColumnName = "id"))
    private List<Domain> domains;

    @ManyToMany
    @JoinTable(name = "cv_referee",  joinColumns = @JoinColumn(name = "curriculum_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "referee_id", referencedColumnName = "id"))
    private List<Referee> referees;

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

    public Date getHtmlGenerated() {
        return htmlGenerated;
    }

    public void setHtmlGenerated(Date htmlGenerated) {
        this.htmlGenerated = htmlGenerated;
    }

    public Date getPdfGenerated() {
        return pdfGenerated;
    }

    public void setPdfGenerated(Date pdfGenerated) {
        this.pdfGenerated = pdfGenerated;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getSeekJob() {
        return seekJob;
    }

    public void setSeekJob(String seekJob) {
        this.seekJob = seekJob;
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

    public String getHtmlPreview() {
        return htmlPreview;
    }

    public void setHtmlPreview(String htmlPreview) {
        this.htmlPreview = htmlPreview;
    }

    public Boolean hasSkillsDescription() {
        for(Domain domain : this.domains){
            if(domain.getDomainType().equals("DESCRIPTION") || domain.getDomainType().equals("LANGUE")){
                return true;
            }
        }
        return false;
    }
}

