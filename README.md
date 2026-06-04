# poc-langchain4j

poc-langchain4j (https://github.com/langchain4j/langchain4j) : langchain4j-gemini-demo

## Setup API KEY

 - Rendez-vous sur Google AI Studio : Allez sur la page dédiée à la création de clés d'API : https://aistudio.google.com/app/apikey
 - Connectez-vous : Utilisez votre compte Google habituel.
 - Créez la clé :
Cliquez sur le bouton bleu "Create API key" (Créer une clé API).
Vous pouvez choisir de la créer dans un nouveau projet (recommandé) ou dans un projet Google Cloud existant.
 - Copiez la clé : Une fois générée, une longue chaîne de caractères s'affichera. Copiez-la.

## API KEY test

```
curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" \
  -H 'Content-Type: application/json' \
  -H 'X-goog-api-key: xxxxx' \
  -X POST \
  -d '{
    "contents": [
      {
        "parts": [
          {
            "text": "Explain how AI works in a few words"
          }
        ]
      }
    ]
  }'

curl https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=xxxxx \
 -H "Content-Type: application/json" \
 -d '{
 "contents": [
   {
 	"parts": [
 	  {
 		"text": "Dis-moi si tu reçois ce message."
 	  }
 	]
   }
 ]
 }'

Compatible avec le standard OpenAI :

curl https://generativelanguage.googleapis.com/v1beta/openai/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer xxxxx" \
  -d '{
    "model": "gemini-flash-latest",
    "messages": [
      {
        "role": "user",
        "content": "Explain how AI works in a few words"
      }
    ]
  }'
```


## Run the demo

 - mvn clean package

```bash
# Sur Windows (CMD) :
set GEMINI_API_KEY=xxx...
java -jar target/langchain4j-gemini-demo-1.0-SNAPSHOT-jar-with-dependencies.jar

# Sur Linux / Mac / Windows (PowerShell) :
export GEMINI_API_KEY="xxx..."
java -jar target/langchain4j-gemini-demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```
