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


    Player getRequester() {
        return playerA;
    }

    Player getTarget() {
        return playerB;
    }
    boolean isSameRequest(Request newRequest){
        return this.playerB.equals(newRequest.playerB)&&this.playerA.equals(newRequest.playerA);
    }
}
