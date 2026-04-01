package org.projeto.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlayerModel {
    int id;
    String player_name;
    int age;
    String team;
    String position;
    int games;
    int games_started;
    Double minutes;
    Double pts;
    Double ast;
    Double orb;
    Double drb;
    Double trb;
    Double stl;
    Double blk;
}
