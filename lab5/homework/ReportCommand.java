package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    @Override
    public void execute(Repository repository, String[] args) throws IOException, InvalidCommand, TemplateException {
        if (args.length < 1) {
            throw new InvalidCommand("Usage: report <filePath>");
        }
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        Template template = cfg.getTemplate("report.ftl");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("images", repository.getImages());

        try (Writer fileWriter = new FileWriter(args[0])) {
            template.process(dataModel, fileWriter);
        }
        System.out.println("Report generated: " + args[0]);
    }
}
