package com.cgi;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args) {

        // 1. Récupération de la clé API
        // Il est fortement recommandé d'utiliser une variable d'environnement pour des raisons de sécurité
        String apiKey = System.getenv("OPENAI_API_KEY");

        // 2. Configuration du modèle OpenAI
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini") // Modèle rapide et performant par défaut
                .temperature(0.7) // Contrôle la créativité (0.0 = très factuel, 1.0 = très créatif)
                .build();

        // 3. Préparation du prompt
        String prompt = "Explique-moi ce qu'est le framework LangChain4j en une seule phrase courte et compréhensible.";

        System.out.println("🤖 Envoi du prompt à OpenAI : \"" + prompt + "\"");
        System.out.println("⏳ En attente de la réponse...\n");

        try {
            // 4. Appel à l'API et affichage de la réponse
            String response = model.generate(prompt);
            System.out.println("✨ Réponse de OpenAI :");
            System.out.println(response);
        } catch (Exception e) {
            System.err.println("❌ Une erreur est survenue lors de l'appel à l'API OpenAI :");
            e.printStackTrace();
        }
    }
}