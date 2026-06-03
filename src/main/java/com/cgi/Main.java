package com.cgi;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Récupération de la clé API
        String apiKey = System.getenv("GEMINI_API_KEY");

        // Configuration du modèle Gemini
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.2)
                .build();
        try {
            // Document
            String nomFichier = "mon_document.txt";
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(nomFichier);

            if (inputStream == null) {
                System.err.println("Fichier '" + nomFichier + "' introuvable dans src/main/resources/");
                return;
            }
            String contenuFichier = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Utilisation d'un PromptTemplate (Fonctionnalité avancée)
            // On définit un "moule" avec des variables {{contexte}} et {{question}}
            String templateStr = "Voici un document de référence :\n" +
                    "---------------------\n" +
                    "{{contexte}}\n" +
                    "---------------------\n" +
                    "En te basant STRICTEMENT sur le document ci-dessus, réponds à la question suivante. " +
                    "Si la réponse n'est pas dans le document, dis 'Je ne sais pas'.\n\n" +
                    "Question : {{question}}";

            PromptTemplate promptTemplate = PromptTemplate.from(templateStr);

            // Test avec "Explique-moi ce qu'est le framework LangChain4j."
            String question = "Explique-moi ce qu'est le framework LangChain4j en une seule phrase courte et compréhensible.";

            // Injection des variables dans le template
            Prompt promptFinal = promptTemplate.apply(Map.of(
                    "contexte", contenuFichier,
                    "question", question
            ));


            System.out.println("\nEnvoi du prompt structuré à Gemini...");

            // Appel à l'API
            String response = model.generate(promptFinal.text());
            System.out.println("\nRéponse de Gemini :");
            System.out.println(response);

        } catch (Exception e) {
            System.err.println("Une erreur est survenue lors de l'appel à l'API Gemini :");
            e.printStackTrace();
        }
    }
}