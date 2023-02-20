package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotTracker {
    private final List<ValueCommand> valueCommands = new ArrayList<>();
    private final List<BotCommand> botCommands = new ArrayList<>();
    private final Map<Integer, Bot> botsAndChipsMap = new HashMap<>();
    private final Map<Integer, Output> outputAndChipsMap = new HashMap<>();

    public List<ValueCommand> getValueCommands() {
        return valueCommands;
    }

    public List<BotCommand> getBotCommands() {
        return botCommands;
    }

    public Map<Integer, Bot> getBotsAndChipsMap() {
        return botsAndChipsMap;
    }

    public Map<Integer, Output> getOutputAndChipsMap() {
        return outputAndChipsMap;
    }

}
