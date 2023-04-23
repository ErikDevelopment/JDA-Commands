import com.github.erikdevelopment.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class TestCommand extends Command {
    public TestCommand() {
        super("test", "/");
    }

    @Override
    public void execute(Guild guild, Member member, MessageChannelUnion messageChannelUnion, GuildMessageChannelUnion guildMessageChannelUnion, String[] args) {
        System.out.println(member.getEffectiveName());
    }
}
