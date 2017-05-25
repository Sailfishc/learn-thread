package com.sailfish.akka.route;


import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;

/**
 * @author sailfish
 * @create 2017-05-25-上午10:25
 */
public class RouterMain {

    public static Agent<Boolean> flag = Agent.create(true, ExecutionContexts.global());

    public static void main(String[] args) throws InterruptedException {

        ActorSystem system = ActorSystem.create("router", ConfigFactory.load("samplehello.conf"));
        ActorRef watcher = system.actorOf(Props.create(WatchActor.class), "watcher");
        int i = 1;
        while (flag.get()) {
            watcher.tell(MyWork.Msg.WORKING, ActorRef.noSender());
            if (i % 10 == 0) {
                watcher.tell(MyWork.Msg.class, ActorRef.noSender());
            }
            i++;
            Thread.sleep(100);
        }
    }
}
