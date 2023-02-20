package input;

import data.BotCommand;
import data.BotTracker;
import data.Destination;
import data.ValueCommand;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InputReader {

    private final BotTracker botTracker;
    private BufferedReader bufferedReader;

    public InputReader(BotTracker botTracker, String filepath) {
        this.botTracker = botTracker;
        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public void readFileCommands() {
        String nextLine;
        try {
            while ((nextLine = bufferedReader.readLine()) != null)
                if (nextLine.startsWith("bot")) {
                    List<String> botCommandLine = Arrays.asList(nextLine.split(" "));
                    botTracker.getBotCommands().add(mapBotCommand(botCommandLine));
                } else {
                    List<String> valueCommandLine = Arrays.asList(nextLine.split(" "));
                    botTracker.getValueCommands().add(mapValueCommand(valueCommandLine));
                }
        } catch (IOException ex) {
            System.out.println("Error while reading file");
        }
    }

    private BotCommand mapBotCommand(List<String> botCommandLine) {
        return new BotCommand(
                Integer.valueOf(botCommandLine.get(1)),
                new Destination(botCommandLine.get(5), Integer.valueOf(botCommandLine.get(6))),
                new Destination(botCommandLine.get(10), Integer.valueOf(botCommandLine.get(11)))
        );
    }

    private ValueCommand mapValueCommand(List<String> valueCommandLine) {
        return new ValueCommand(Integer.valueOf(valueCommandLine.get(5)), Integer.valueOf(valueCommandLine.get(1)));
    }
}
