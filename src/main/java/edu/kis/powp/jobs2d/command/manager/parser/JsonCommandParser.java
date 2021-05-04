package edu.kis.powp.jobs2d.command.manager.parser;

import com.google.gson.Gson;

public class JsonCommandParser implements IParser {
    private Gson gson = new Gson();

    @Override
    public DataModel parse(String data) {
        return gson.fromJson(data, DataModel.class);
    }
}
