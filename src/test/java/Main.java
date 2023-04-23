import com.github.erikdevelopment.CommandRegistry;
import com.github.erikdevelopment.JDACommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EventListener;

public class Main {
    public static void main(String[] args)
            throws InterruptedException
    {
        // JDA Code - start
        JDABuilder build = JDABuilder.createDefault("#token");
        // JDA Code - stop
        JDA application = build.build();
        JDACommands jdaCommands = JDACommands.init(application); // Initialize JDA Commands
        CommandRegistry registry = jdaCommands.getCommandRegistry();
        registry.registerCommand(new TestCommand());
    }
}
