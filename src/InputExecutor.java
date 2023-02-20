import data.BotTracker;
import input.InputProcessor;
import input.InputReader;

public class InputExecutor {

    public static void main(String[] args) {
        BotTracker botTracker = new BotTracker();
        InputReader reader = new InputReader(botTracker, "src/input/instructions/inputBots.txt");
        InputProcessor processor = new InputProcessor(botTracker);

        reader.readFileCommands();
        processor.processValueCommands();
        processor.processBotCommands();
        processor.print();
    }

}
