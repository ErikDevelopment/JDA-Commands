import com.github.erikdevelopment.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class TestCommand extends Command{
    public TestCommand() {
        super("test", "/");
    }

    @Override
    public void execute(Guild guild, Member member, Message message, MessageChannelUnion channel, GuildMessageChannelUnion guildChannel, InteractionHook interactionHook, String[] arguments) {
        System.out.println(member.getEffectiveName());
    }
}
