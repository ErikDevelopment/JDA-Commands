package com.github.erikdevelopment.listener;

import com.github.erikdevelopment.Command;
import com.github.erikdevelopment.JDACommands;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;

import java.util.Arrays;

public class MessageReceivedListener extends ListenerAdapter {
    public void onMessageReceived(final MessageReceivedEvent event) {
        final String message = event.getMessage().getContentStripped();
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(message);

        if (command == null || command.getPrefix().startsWith("/"))
            // The command is not exists or a slash command!
            return;

        // Check if member has access
        final Member member = event.getMember();
        if (member == null) return; // The member cannot be null
        if (command.getAccess().length != 0
                && member.getRoles().stream().filter(role -> Arrays.stream(command.getAccess()).anyMatch(role1 -> role1 == role)).count() <= 0L
        )
            // Member has no access of to the command
            return;
        InteractionHook interactionHook = null;
        // Execute the command
        final String[] splitMessage = message.split(" ");
        command.execute(
                event.getGuild(),
                member,
                event.getMessage(),
                event.getChannel(),
                event.getGuildChannel(),
                interactionHook,
                Arrays.copyOfRange(splitMessage, 1, splitMessage.length)
        );
    }
}
