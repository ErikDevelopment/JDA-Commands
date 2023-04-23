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
        // Note: It is important to register your ReadyListener before building
        JDA jda = JDABuilder.createDefault("$token").build();
        jda.addEventListener(new CommandRegistry());

        // optionally block until JDA is ready
        JDACommands.init(jda); // Initialize JDA Commands
        jda.awaitReady();
    }
}
