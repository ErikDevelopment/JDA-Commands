package com.github.erikdevelopment.listener;

import com.github.erikdevelopment.Command;
import com.github.erikdevelopment.JDACommands;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class SlashCommandInteractionListener extends ListenerAdapter {
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
        //Define values
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(event.getName().toLowerCase());

        if (command == null || !command.getPrefix().startsWith("/"))
            // The command is not a slash command!
            return;

        // Check if member has access
        final Member member = event.getMember();
        if (member == null) return; // The member cannot be null
        if (command.getAccess().length != 0
                && member.getRoles().stream().filter(role -> Arrays.stream(command.getAccess()).anyMatch(role1 -> role1 == role)).count() <= 0L
        )
            // Member has no access of to the command
            return;

        Message message = null;
        InteractionHook interactionHook = null;
        event.deferReply().queue(interactionHook1 -> {
            interactionHook1 = interactionHook;
        });
        command.execute(
                event.getGuild(),
                member,
                message,
                event.getChannel(),
                event.getGuildChannel(),
                event.getHook(),
                ArrayUtils.addAll(
                        event.getSubcommandGroup() != null ? event.getSubcommandGroup().split(" ") : new String[] {},
                        event.getSubcommandName() != null ? event.getSubcommandName().split(" ") : new String[] {}
                )
        );
    }
}
