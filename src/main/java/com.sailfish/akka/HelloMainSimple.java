package com.sailfish.akka;

import com.typesafe.config.ConfigFactory;

import sun.jvm.hotspot.HelloWorld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author sailfish
 * @create 2017-05-19-下午12:25
 */
public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloworld");
        System.out.println("Helloworld actor path:" + a.path());
    }
}
/*
Helloworld actor path:akka://Hello/user/helloworld
greeter path:akka://Hello/user/helloworld/greeter
hello world
hello world
[INFO] [05/19/2017 12:27:23.735] [Hello-akka.actor.default-dispatcher-2] [akka://Hello/user/helloworld] Message [com.sailfish.akka.Greeter$Msg] from Actor[akka://Hello/user/helloworld/greeter#-2057003949] to Actor[akka://Hello/user/helloworld#2141564743] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/19/2017 14:29:12.248] [Thread-0] [CoordinatedShutdown(akka://Hello)] Starting coordinated shutdown from JVM shutdown hook
 */