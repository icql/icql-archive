package work.icql.ssm.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.service.FreemarkService;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/20 9:34
 * @Title FreemarkServiceImpl
 * @Description FreemarkServiceImpl
 */
@Service
public class FreemarkServiceImpl implements FreemarkService {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public IcqlResult getHtml() {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        try {
            Template template = configuration.getTemplate("index.ftl");
            Map root = new HashMap<>();
            root.put("title", "freemarker index");
            root.put("test", "freemarker test");

            //String classesPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
            //String outPath = classesPath.replace("classes/", "jsp/freemarker/html");

            String outPath = "C:\\Users\\a6924.GOLDDRAGON.COM\\Desktop";
            Writer out = new FileWriter(new File(outPath, "index.html"));
            template.process(root, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return IcqlResult.build(500, "freemarker html生成失败");
        }
        return IcqlResult.ok();
    }
}
