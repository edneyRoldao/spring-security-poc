package com.edntisolutions.aouth.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Player {

    private int id;
    private String name;
    private String team;

    public static List<Player> getMockPlayers() {
        List<Player> players = new ArrayList<>();

        players.add(Player.builder().id(1).name("Messi").team("barcelona").build());
        players.add(Player.builder().id(1).name("Ronaldo").team("real madrid").build());
        players.add(Player.builder().id(1).name("Kobe").team("lakers").build());

        return players;
    }

}
