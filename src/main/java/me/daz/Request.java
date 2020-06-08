package me.daz;

import org.bukkit.entity.Player;

public class Request {
    private final Player playerB;
    private final Player playerA;

    Request(Player playerB, Player playerA) {
        this.playerB = playerB;
        this.playerA = playerA;
    }

    Player getPlayerB(){return playerB;}
    Player getPlayerA(){return playerA;}


}
