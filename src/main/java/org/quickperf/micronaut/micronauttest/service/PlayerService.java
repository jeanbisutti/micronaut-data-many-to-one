/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2020-2020 the original author or authors.
 */

package org.quickperf.micronaut.micronauttest.service;

import org.quickperf.micronaut.micronauttest.dto.PlayerWithTeamName;
import org.quickperf.micronaut.micronauttest.jdbc.entity.Player;
import org.quickperf.micronaut.micronauttest.jdbc.entity.Team;
import org.quickperf.micronaut.micronauttest.jdbc.repository.PlayerRepository;
import org.quickperf.micronaut.micronauttest.jdbc.repository.TeamRepository;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerWithTeamName> findPlayersWithTeamName() {

        Iterable<Player> players = playerRepository.findAll();

        List<PlayerWithTeamName> playersWithTeamName = new ArrayList<>();
        for (Player aPlayer : players) {
            Team team = aPlayer.getTeam();
            Long teamId = team.getId();
            System.out.println("teamId = " + teamId);
            String teamName = team.getName();
            System.out.println("teamName = " + teamName);
            PlayerWithTeamName playerWithTeamName
                    = new PlayerWithTeamName(aPlayer.getFirstName()
                                           , aPlayer.getLastName()
                                           , teamName);
            playersWithTeamName.add(playerWithTeamName);
        }

        return playersWithTeamName;

    }

}
