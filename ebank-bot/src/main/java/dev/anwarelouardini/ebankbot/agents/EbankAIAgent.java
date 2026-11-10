package dev.anwarelouardini.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

@Component
public class EbankAIAgent {
    // Pour communiquer avec n'importe quelle LLM
    private ChatClient chatClient;

    public EbankAIAgent(ChatClient.Builder chatClient, ToolCallbackProvider tools, ChatMemory chatMemory) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous êtes un assistant qui se charge de répondre aux questions de l'utilisateur a propos des clients et des comptes bancaires, en fonction du contexte fourni. Si aucun contexte n'est fourni, réponds avec "JE NE SAIS PAS"
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "DEFAULT"))
                .defaultTools(tools)
                .build();
    }

    public String chat(Prompt prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
