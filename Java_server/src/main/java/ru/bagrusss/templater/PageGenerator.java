package ru.bagrusss.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author v.chibrikov
 */

public class PageGenerator {

    private static final String HTML_DIR = "server_tml";
    private static final Configuration SERVER_CONFIGS = new Configuration();

    @Nullable
    @SuppressWarnings("ObjectToString")
    public static String getPage(@NotNull String filename, @Nullable Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = SERVER_CONFIGS != null ? SERVER_CONFIGS.getTemplate(HTML_DIR + File.separator + filename) : null;
            if (template != null) {
                template.process(data, stream);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
