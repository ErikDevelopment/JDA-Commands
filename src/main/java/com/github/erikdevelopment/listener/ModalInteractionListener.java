package com.github.erikdevelopment.listener;

import com.github.erikdevelopment.Command;
import com.github.erikdevelopment.JDACommands;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ModalInteractionListener extends ListenerAdapter {
    public void onModalInteraction(final ModalInteractionEvent event) {
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(event.getModalId());

        final Member member = event.getMember();
        if (member == null) return; // The member cannot be null
        if (command.getAccess().length != 0
                && member.getRoles().stream().filter(role -> Arrays.stream(command.getAccess()).anyMatch(role1 -> role1 == role)).count() <= 0L
        )
            // Member has no access of to the command
            return;

        // Execute the command
        command.execute(
                event.getGuild(),
                member,
                event.getChannel(),
                event.getGuildChannel(),
                ArrayUtils.addAll(
                        new String[] {event.getMessage().getId()},
                        new String[] {event.getModalId()}
                )
        );
    }
}
