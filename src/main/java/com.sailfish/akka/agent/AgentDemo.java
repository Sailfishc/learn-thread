//package com.sailfish.akka.agent;
//
//
//import com.typesafe.config.ConfigFactory;
//
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.actor.Inbox;
//import akka.actor.Props;
//import akka.actor.Terminated;
//import akka.agent.Agent;
//import akka.dispatch.ExecutionContexts;
//import akka.dispatch.Futures;
//import akka.dispatch.OnComplete;
//import scala.concurrent.Future;
//import scala.concurrent.duration.Duration;
//
///**
// * @author sailfish
// * @create 2017-05-25-下午12:01
// */
//public class AgentDemo {
//
//    public static Agent<Integer> countAgent = Agent.create(0, ExecutionContexts.global());
//    static ConcurrentLinkedQueue<Future<Integer>> futures = new ConcurrentLinkedQueue<>();
//
//    public static void main(String[] args) throws TimeoutException {
//        final ActorSystem system = ActorSystem.create("agentdemo", ConfigFactory.load("samplehello.conf"));
//
//        ActorRef[] count = new ActorRef[10];
//        for (int i = 0; i< count.length; i++) {
//            count[i] = system.actorOf(Props.create(CountActor.class), "counter_" + i);
//        }
//
//        Inbox inbox = Inbox.create(system);
//        for (int i = 0; i < count.length; i++) {
//            //出发CountActor累加动作，send和alert都可以向agent发送一个修改动作，但是send没有返回值，alert会返回一个future
//            inbox.send(count[i], 1);
//            inbox.watch(count[i]);
//        }
//
//        int closeCount = 0;
//        //等待所有actor全部结束
//        while (true) {
//            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
//            if (msg instanceof Terminated) {
//                closeCount++;
//                if (closeCount == count.length) {
//                    break;
//                }
//            } else {
//                System.out.println(msg);
//            }
//        }
//
//        //等待所有的线程都结束，因为他们都是异步的
//        //sequence是串行操作构造一个整体的Future，并为它创建一个回调函数
//        Futures.sequence(futures, system.dispatcher()).onComplete(
//                new OnComplete<Iterable<Integer>>() {
//                    @Override
//                    public void onComplete(Throwable throwable, Iterable<Integer> integers) throws Throwable {
//                        System.out.println("countAgent=" + countAgent.get());
////                        system.
//                    }
//                }, system.dispatcher()
//        );
//    }
//
//}
