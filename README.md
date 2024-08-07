# DelightAI-Android

## Installation 💻

### Clone the project

1. Clone the project.
2. In Android Studio, choose File -> New -> Import Module.
3. Done.

## Usage Example

Import the module in your project:

```kotlin
import com.delightai.api.ChatBuilder
import com.delightai.api.PollingBuilder
```

```koltin
GlobalScope.launch {
    val build = ChatBuilder().build() 
    val pollingBuild = PollingBuilder().build()
    val response = build.send(
                        text = text, // text to DelightAI
                        webhook_id = webhook_id, // a demo webhook Id, you can use it for testing
                        user_id = user_id, // user id that send to DelightAI
                        username = username, // username that send to DelightAI
                        message_id = message_id) // message id 
    withContext(Dispatchers.IO) {
        val pollingResponse = pollingBuild.polling(webhook_id = response.poll)
        if (pollingResponse?.completed == true) {
            // do something
        }
    }
}
```
