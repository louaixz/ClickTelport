package me.daz;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestManager {
    private final ConcurrentHashMap<Request, Date> pendingRequests;

    RequestManager() {
        pendingRequests = new ConcurrentHashMap<>();
    }

}