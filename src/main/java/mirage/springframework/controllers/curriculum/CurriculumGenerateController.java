package mirage.springframework.controllers.curriculum;

import mirage.springframework.domain.Curriculum;
import mirage.springframework.services.data.CurriculumService;
import mirage.springframework.services.pdfGenerator.CurriculumGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mirage on 19/12/2016.
 */
@Controller
public class CurriculumGenerateController {

    private CurriculumGeneratorService curriculumGeneratorService;
    private CurriculumService curriculumService;

    @Autowired
    public void setCurriculumGeneratorService(CurriculumGeneratorService curriculumGeneratorService) {
        this.curriculumGeneratorService = curriculumGeneratorService;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @RequestMapping("/curriculum/preview/{id}")
    public String preview(@PathVariable Integer id, Model model){
        Curriculum curriculum = curriculumService.getById(id);
        model.addAttribute("curriculum", curriculum);
        model.addAttribute("skillDescription", curriculum.hasSkillsDescription());

        return "cvs/cv-template1";
    }

    @RequestMapping("/curriculum/generate/{id}")
    public String generateCurriculumToPdf(@PathVariable Integer id){

        curriculumGeneratorService.generateCurriculumToPdf(id);

        return "redirect:/curriculum/view/" + id;
    }
}
