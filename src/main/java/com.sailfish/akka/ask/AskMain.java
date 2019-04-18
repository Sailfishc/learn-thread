//package com.sailfish.akka.ask;
//
//import com.sailfish.akka.route.MyWork;
//import com.sailfish.akka.route.WatchActor;
//import com.typesafe.config.ConfigFactory;
//
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.actor.Props;
//
///**
// * @author sailfish
// * @create 2017-05-25-上午11:47
// */
//public class AskMain {
//
//    public static void main(String[] args) {
//        ActorSystem system = ActorSystem.create("askdemo", ConfigFactory.load("samplehello.conf"));
//        ActorRef worker = system.actorOf(Props.create(MyWork.class), "worker");
//        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
//
//        //  等待future返回
////        ask
//    }
//}
