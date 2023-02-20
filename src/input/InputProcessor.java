package input;

import data.*;
import java.util.*;
import java.util.stream.Collectors;

public class InputProcessor {

    private final BotTracker botTracker;

    public InputProcessor(BotTracker botTracker) {
        this.botTracker = botTracker;
    }

    public void processValueCommands() {
        for (ValueCommand valueCommand : botTracker.getValueCommands()) {
            Bot botToAdd = getBot(valueCommand.activeBotNumber());
            addChip(botToAdd, valueCommand.chipNumber());
            botTracker.getBotsAndChipsMap().put(valueCommand.activeBotNumber(), botToAdd);
        }
    }

    public void processBotCommands() {
        List<BotCommand> botCommands = botTracker.getBotCommands();
        while (!botCommands.isEmpty()) {
            for (BotCommand botCommand : botCommands) {
                Bot givingBot = getBot(botCommand.givingBotNumber());
                if (givingBot.getLowChip() != null && givingBot.getHighChip() != null) {
                    divideChips(botCommand.givingBotNumber(), botCommand.lowChipReceiver(), botCommand.highChipReceiver());
                    botCommands = botCommands.stream().filter(command -> !botCommand.equals(command)).collect(Collectors.toList());
                }
            }
        }
    }

    public void addChip(Bot bot, Integer newChip) {
        if (bot.getLowChip() == null) {
            bot.setLowChip(newChip);
        } else {
            Integer comparedChip;
            if (bot.getLowChip() < newChip) {
                bot.setHighChip(newChip);
            } else {
                comparedChip = bot.getLowChip();
                bot.setLowChip(newChip);
                bot.setHighChip(comparedChip);
            }
        }
    }

    private void divideChips(Integer givingBotNumber, Destination destinationLowChip, Destination destinationHighChip) {
        Bot givingBot = getBot(givingBotNumber);
        addChipToDestination(destinationLowChip, givingBot.getLowChip());
        addChipToDestination(destinationHighChip, givingBot.getHighChip());
        givingBot.setLowChip(null);
        givingBot.setHighChip(null);
    }

    private void addChipToDestination(Destination destination, Integer chip) {
        if (destination.type().equals("bot")) {
            Bot receivingBot = getBot(destination.number());
            addChip(receivingBot, chip);
            botTracker.getBotsAndChipsMap().put(destination.number(), receivingBot);
        } else if (destination.type().equals("output")) {
            botTracker.getOutputAndChipsMap().put(destination.number(), new Output(destination.number(), chip));
        }
    }

    public Bot getBot(Integer botNumber) {
        Bot bot = botTracker.getBotsAndChipsMap().get(botNumber);
        if (bot == null) {
            bot = new Bot(botNumber);
            botTracker.getBotsAndChipsMap().put(botNumber, bot);
        }
        return bot;
    }

    public void print() {
        System.out.println("Output 0 holds chip " + botTracker.getOutputAndChipsMap().get(0).chipNumber());
        System.out.println("Output 1 holds chip " + botTracker.getOutputAndChipsMap().get(1).chipNumber());
        System.out.println("Output 1 holds chip " + botTracker.getOutputAndChipsMap().get(2).chipNumber());
        int result = botTracker.getOutputAndChipsMap().get(0).chipNumber() * botTracker.getOutputAndChipsMap().get(1).chipNumber() * botTracker.getOutputAndChipsMap().get(2).chipNumber();
        System.out.println("Multiplying the values of the chips gives " + result + " as outcome.");
    }
}
