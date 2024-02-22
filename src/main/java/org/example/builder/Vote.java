package org.example.builder;

import org.example.entities.Votes;

public class Vote {

    public static Votes createVote(){
        return Votes.builder()
                .imageId("qwerty2")
                .subId("my-user-366")
                .value("17")
                .build();
    }

}
