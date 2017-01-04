package mirage.springframework.services.pdfGenerator.implementation;

import mirage.springframework.domain.*;
import mirage.springframework.services.data.CurriculumService;
import mirage.springframework.services.data.UserDetailsService;
import mirage.springframework.services.pdfGenerator.XmlGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

/**
 * Created by Mirage on 19/12/2016.
 */
@Service
public class XmlGeneratorServiceImpl implements XmlGeneratorService{

    private CurriculumService curriculumService;

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Override
    public File generateXml(Integer curriculumId) {

        Curriculum curriculum = curriculumService.getById(curriculumId);

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element cvElement = document.createElement("curriculum");

            document.appendChild(cvElement);

            //User information
            User user = curriculum.getUserDetails().getUser();

            Element userXml = getUserElements(document, user);

            cvElement.appendChild(userXml);

            createNodeElement(document, cvElement, "seekjob", curriculum.getSeekJob());

            createNodeElement(document, cvElement, "introduction", curriculum.getIntroduction().toString());

         /*   List<Skill> skills = curriculum.getSkills();
            Element skillsXml = getSkillsElements(document, skills);

            cvElement.appendChild(skillsXml);*/

            List<Experience> experiences = curriculum.getExperiences();
            Element experiencesXml = getExperiencesElements(document, experiences);

            cvElement.appendChild(experiencesXml);

            List<Education> educations = curriculum.getEducations();
            Element educationXml = getEducationsElements(document, educations);

            cvElement.appendChild(educationXml);

            createNodeElement(document, cvElement, "interests", curriculum.getInterests().toString());

            List<Referee> referees = curriculum.getReferees();
            Element refereeXml = getRefereesElements(document, referees);

            cvElement.appendChild(refereeXml);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            //StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            //System.out.println("File saved!");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return null;
    }

    private Element getRefereesElements(Document document, List<Referee> referees) {

        Element refereesXml = document.createElement("referees");

        for(Referee referee : referees) {

            Element refereeElm = document.createElement("referee");

            createNodeElement(document, refereeElm, "company", referee.getCompany());
            createNodeElement(document, refereeElm, "fullName", referee.getFullName());
            createNodeElement(document, refereeElm, "function", referee.getFunction());
            createNodeElement(document, refereeElm, "email", referee.getEmail());
            createNodeElement(document, refereeElm, "phoneNumber", referee.getPhoneNumber());
            createNodeElement(document, refereeElm, "website", referee.getWebsite());

            refereesXml.appendChild(refereeElm);
        }

        return refereesXml;

    }

    private Element getSkillsElements(Document document, List<Skill> skills) {

        Element skillsXml = document.createElement("skills");

        for(Skill skill : skills) {

            Element skillElm = document.createElement("skill");

            createNodeElement(document, skillElm, "title", skill.getTitle());
            createNodeElement(document, skillElm, "rank", skill.getRank().toString());
            createNodeElement(document, skillElm, "description", skill.getDescription());
            skillsXml.appendChild(skillElm);
        }

        return skillsXml;
    }

    private Element getEducationsElements(Document document, List<Education> educations) {

        Element educationsXml = document.createElement("educations");

        for(Education education : educations){

            Element educationElm = document.createElement("education");

            createNodeElement(document, educationElm, "title", education.getTitle());
            createNodeElement(document, educationElm, "school", education.getSchool());
            createNodeElement(document, educationElm, "schoolurl", education.getSchoolUrl());
            createNodeElement(document, educationElm, "startdate", education.getStartDate().toString());
            createNodeElement(document, educationElm, "endDate", education.getEndDate().toString());
            createNodeElement(document, educationElm, "description", education.getDescription());
            createNodeElement(document, educationElm, "achievement1", education.getAchievement1());
            createNodeElement(document, educationElm, "achievement2", education.getAchievement2());
            createNodeElement(document, educationElm, "achievement3", education.getAchievement3());
            createNodeElement(document, educationElm, "result", education.getResult());

            educationsXml.appendChild(educationElm);
        }

        return educationsXml;
    }

    private Element getExperiencesElements(Document document, List<Experience> experiences) {

        Element experiencesXml = document.createElement("experiences");

        for(Experience experience : experiences){
            Element experienceElm = document.createElement("experience");

            createNodeElement(document, experienceElm, "title", experience.getTitle());
            createNodeElement(document, experienceElm, "company", experience.getCompany());
            createNodeElement(document, experienceElm, "urlCompany", experience.getUrlCompany());
            createNodeElement(document, experienceElm, "location", experience.getLocation());
            createNodeElement(document, experienceElm, "startdate", experience.getStartDate().toString());
            createNodeElement(document, experienceElm, "endDate", experience.getEndDate().toString());
            createNodeElement(document, experienceElm, "responsibilities", experience.getResponsibilities());
            createNodeElement(document, experienceElm, "achievement1", experience.getAchievement1());
            createNodeElement(document, experienceElm, "achievement2", experience.getAchievement2());
            createNodeElement(document, experienceElm, "achievement3", experience.getAchievement3());

            experiencesXml.appendChild(experienceElm);
        }

        return experiencesXml;
    }

    private Element getUserElements(Document document, User user) {

        Element userXml = document.createElement("user");

        createNodeElement(document, userXml, "firstname", user.getFirstName());
        createNodeElement(document, userXml, "lastname", user.getLastName());
        createNodeElement(document, userXml, "email", user.getEmail());
        createNodeElement(document, userXml, "phonenumber", user.getPhoneNumber());
        createNodeElement(document, userXml, "address", user.getAddress());
        createNodeElement(document, userXml, "addrescomplement", user.getAddressComplement());
        createNodeElement(document, userXml, "city", user.getCity());
        createNodeElement(document, userXml, "zipcode",user.getZipCode());
        createNodeElement(document, userXml, "country",user.getCountry());

        return userXml;

    }

    private void createNodeElement(Document document, Element elementXml, String nodeName, String nodeValue) {
        Element element = document.createElement(nodeName);
        if(isNotNull(nodeValue)) {
            element.appendChild(document.createTextNode(nodeValue));
        }else {
            element.appendChild(document.createTextNode(" "));
        }
        elementXml.appendChild(element);
    }

    private boolean isNotNull(String entry){
        if(entry != null){
            return true;
        }
        return false;
    }
}
