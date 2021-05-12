package edu.kis.powp.jobs2d.command.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonCommandImporter implements CommandImporter {
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(DriverCommand.class, new JsonCommandSerializer())
			.setPrettyPrinting()
			.create();

	@Override
	public CompoundCommand importCommand(String command) {
		return gson.fromJson(command, CompoundCommand.class);
	}

	public String exportCommand(CompoundCommand command) {
		return gson.toJson(command);
	}

	public static String loadFileContent(String filepath) throws IOException {
		return new Scanner(new File(filepath)).useDelimiter("\\Z").next();
	}

	public static void writeFileContent(String filepath, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		writer.write(content);
		writer.close();
	}
}
