package tech.stl.hcm.message.broker.consumer;


import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListenerBootstrapper implements ApplicationRunner {

    private final ListenerService listenerService;
    private final List<MessageHandler<?>> messageHandlers;
    private final Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ListenerBootstrapper initialized.");
        for (MessageHandler<?> handler : messageHandlers) {
            TopicListener annotation = handler.getClass().getAnnotation(TopicListener.class);
            if (annotation != null) {
                // Resolve enableProperty
                String enableProp = annotation.enableProperty();
                boolean enabled = true;

                if (!enableProp.isEmpty()) {
                    String resolved = environment.getProperty(enableProp);
                    enabled = Boolean.parseBoolean(resolved);
                }

                if (!enabled) {
                    log.info("Skipping listener for topic {} as it's disabled via config", annotation.topic());
                    continue;
                }

                String topic = environment.resolvePlaceholders(annotation.topic());
                String groupId = environment.resolvePlaceholders(annotation.groupId());
                log.info("Registering listener for topic {} with group {}", topic, groupId);
                start(handler, topic, groupId, annotation.valueType());
            }
        }
    }


    @SuppressWarnings("unchecked")
    private <T> void start(MessageHandler<T> handler, String topic, String groupId, Class<?> valueType) {
        listenerService.startListening(topic, groupId, (Class<T>) valueType, handler);
    }
}


