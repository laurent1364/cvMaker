package mirage.springframework.services.pdfGenerator.implementation;

import mirage.springframework.domain.Curriculum;
import mirage.springframework.services.data.CurriculumService;
import mirage.springframework.services.pdfGenerator.CurriculumGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

/**
 * Created by Mirage on 26/12/2016.
 */
@Service
public class CurriculumGeneratorImpl implements CurriculumGeneratorService {

    private CurriculumService curriculumService;

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Override
    public String generateCurriculumToPdf(Integer id) {

        Curriculum curriculum = curriculumService.getById(id);

        try {
            //Set URL INFO
            String URL = "http://localhost:8080/curriculum/preview/" + curriculum.getId();
            Long NOW = new Date().getTime();
            String FILE_NAME = curriculum.getTitle() + '_' + NOW;
            String FILE_WRITER = "E://dev/workspace/mirage/cvMaker/data/js/demo.js";


            StringBuffer buffer = new StringBuffer("var page = require('webpage').create();");

            buffer.append(" page.paperSize = {format: 'A4',orientation: 'portrait',margin: '0cm'};");

            buffer.append("page.open('" + URL + "', function () {");
            buffer.append("page.render('CV/" + FILE_NAME + ".pdf');");
            buffer.append("phantom.exit();");
            buffer.append("});");

            //Delete Content From JS File
            File writer = new File(FILE_WRITER);
            writer.delete();

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_WRITER, true));
            bw.write(buffer.toString());
            bw.close();

            System.out.println("PDF URL: E://dev/workspace/mirage/cvMaker/data/js/CV/" + FILE_NAME + ".pdf");

            // Execute command
            String command = "cmd /c start E://dev/workspace/mirage/cvMaker/data/js/my.bat";

            Process child = Runtime.getRuntime().exec(command);

            // Get output stream to write from it
            OutputStream out = child.getOutputStream();

            out.write("cd C:/ /r/n".getBytes());
            out.flush();
            out.write("dir /r/n".getBytes());
            out.close();
        } catch (IOException e) {
        }

        return null;

    }
}
