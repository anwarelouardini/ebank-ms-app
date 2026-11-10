package dev.anwarelouardini.ebankbot.controllers;

import dev.anwarelouardini.ebankbot.agents.EbankAIAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankChatBotController {
    private EbankAIAgent ebankAIAgent;

    public  EbankChatBotController(EbankAIAgent ebankAIAgent) {
        this.ebankAIAgent = ebankAIAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(
            @RequestParam(name = "query", defaultValue = "Bonjour") String query
    ) {
        return ebankAIAgent.chat(new Prompt(query));
    };
}
