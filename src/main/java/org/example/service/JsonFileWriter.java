package org.example.service;

import org.example.model.DndCharacter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

public class JsonFileWriter {

    public void write(Path path, List<DndCharacter> characters) throws Exception {
        JSONArray arr = new JSONArray();

        for (DndCharacter c : characters) {
            JSONObject obj = new JSONObject();
            obj.put("name", c.getName());
            obj.put("race", c.getRace().name());
            obj.put("class", c.getCharacterClass().name());

            JSONObject stats = new JSONObject();
            stats.put("strength", c.getStats().getStrength());
            stats.put("agility", c.getStats().getAgility());
            stats.put("intelligence", c.getStats().getIntelligence());

            obj.put("stats", stats);
            arr.add(obj);
        }

        JSONObject root = new JSONObject();
        root.put("characters", arr);

        Path parent = path.getParent();
        if (parent != null) parent.toFile().mkdirs();

        try (FileWriter fw = new FileWriter(path.toFile())) {
            fw.write(root.toJSONString());
        }
    }
}
