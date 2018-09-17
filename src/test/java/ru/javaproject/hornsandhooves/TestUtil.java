package ru.javaproject.hornsandhooves;

import org.springframework.test.web.servlet.ResultActions;
import ru.javaproject.hornsandhooves.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;

public class TestUtil {
    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }
}
