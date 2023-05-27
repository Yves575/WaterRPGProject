package lu.uni.rpg.event;

import javafx.scene.input.KeyCode;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<Class<? extends Event>, List<MethodContainer>> eventHandlers;
    private boolean[] keyStates;

    public EventManager() {
        eventHandlers = new HashMap<>();
        keyStates = new boolean[KeyCode.values().length];
    }

    public void callEvent(Event event) {
        List<MethodContainer> handlers = eventHandlers.get(event.getClass());
        if (handlers != null) {
            for (MethodContainer handler : handlers) {
                try {
                    handler.getMethod().invoke(handler.getInstance(), event);
                    if (event.isCancelled()) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keyStates[keyCode.ordinal()];
    }

    public void registerEvents(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && Event.class.isAssignableFrom(parameterTypes[0])) {
                    Class<? extends Event> eventClass = parameterTypes[0].asSubclass(Event.class);
                    List<MethodContainer> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new ArrayList<>());
                    handlers.add(new MethodContainer(listener, method));
                }
            }
        }
    }

    private static class MethodContainer {
        private final Object instance;
        private final Method method;

        public MethodContainer(Object instance, Method method) {
            this.instance = instance;
            this.method = method;
        }

        public Object getInstance() {
            return instance;
        }

        public Method getMethod() {
            return method;
        }
    }
}
