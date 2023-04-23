import com.github.erikdevelopment.JDACommands;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        final com.github.erikdevelopment.CommandRegistry registry = JDACommands.getInstance().getCommandRegistry(); // Here the CommandRegistry class is called.

        registry.registerCommand(new TestCommand()); // At this point the TestCommand is registered.
    }
}
