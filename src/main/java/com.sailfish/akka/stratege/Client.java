package com.sailfish.akka.stratege;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author sailfish
 * @create 2017-05-25-上午8:08
 */
public class Client {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lifecycle",
                ConfigFactory.load("lifecycle.conf"));

        customerStratege(system);
    }


    public static void customerStratege(ActorSystem system) {
        ActorRef ref = system.actorOf(Props.create(Supervisor.class), "Supervisor");
        ref.tell(Props.create(RestartActor.class), ActorRef.noSender());

        ActorSelection actorSelection = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");

        for (int i = 0; i < 100; i ++) {
            actorSelection.tell(RestartActor.Msg.RESTART, ActorRef.noSender());

        }
    }
}
/*
prestart hashcode845291946
NullPointerException, restart
prerestart hashcode845291946
prestart hashcode1846278667
postrestart hashcode1846278667
NullPointerException, restart
prerestart hashcode1846278667
prestart hashcode1803794656
postrestart hashcode1803794656
NullPointerException, restart
prerestart hashcode1803794656
prestart hashcode803695793
postrestart hashcode803695793
NullPointerException, restart
poststop hashcode803695793
[ERROR] [05/25/2017 08:16:54.060] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] null
java.lang.NullPointerException
	at com.sailfish.akka.stratege.RestartActor.onReceive(RestartActor.java:45)
	at akka.actor.UntypedAbstractActor$$anonfun$receive$1.applyOrElse(AbstractActor.scala:243)
	at akka.actor.Actor$class.aroundReceive(Actor.scala:513)
	at akka.actor.AbstractActor.aroundReceive(AbstractActor.scala:132)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:519)
	at akka.actor.ActorCell.invoke(ActorCell.scala:488)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)

[ERROR] [05/25/2017 08:16:54.068] [lifecycle-akka.actor.default-dispatcher-4] [akka://lifecycle/user/Supervisor/restartActor] null
java.lang.NullPointerException
	at com.sailfish.akka.stratege.RestartActor.onReceive(RestartActor.java:45)
	at akka.actor.UntypedAbstractActor$$anonfun$receive$1.applyOrElse(AbstractActor.scala:243)
	at akka.actor.Actor$class.aroundReceive(Actor.scala:513)
	at akka.actor.AbstractActor.aroundReceive(AbstractActor.scala:132)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:519)
	at akka.actor.ActorCell.invoke(ActorCell.scala:488)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)

[ERROR] [05/25/2017 08:16:54.069] [lifecycle-akka.actor.default-dispatcher-4] [akka://lifecycle/user/Supervisor/restartActor] null
java.lang.NullPointerException
	at com.sailfish.akka.stratege.RestartActor.onReceive(RestartActor.java:45)
	at akka.actor.UntypedAbstractActor$$anonfun$receive$1.applyOrElse(AbstractActor.scala:243)
	at akka.actor.Actor$class.aroundReceive(Actor.scala:513)
	at akka.actor.AbstractActor.aroundReceive(AbstractActor.scala:132)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:519)
	at akka.actor.ActorCell.invoke(ActorCell.scala:488)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)

[ERROR] [05/25/2017 08:16:54.069] [lifecycle-akka.actor.default-dispatcher-4] [akka://lifecycle/user/Supervisor/restartActor] null
java.lang.NullPointerException
	at com.sailfish.akka.stratege.RestartActor.onReceive(RestartActor.java:45)
	at akka.actor.UntypedAbstractActor$$anonfun$receive$1.applyOrElse(AbstractActor.scala:243)
	at akka.actor.Actor$class.aroundReceive(Actor.scala:513)
	at akka.actor.AbstractActor.aroundReceive(AbstractActor.scala:132)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:519)
	at akka.actor.ActorCell.invoke(ActorCell.scala:488)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at akka.dispatch.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at akka.dispatch.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at akka.dispatch.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at akka.dispatch.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)

[INFO] [05/25/2017 08:16:54.073] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [2] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [3] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [4] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [5] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [6] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [7] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.074] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [8] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.075] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [9] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:54.075] [lifecycle-akka.actor.default-dispatcher-3] [akka://lifecycle/user/Supervisor/restartActor] Message [com.sailfish.akka.stratege.RestartActor$Msg] without sender to Actor[akka://lifecycle/user/Supervisor/restartActor#-2107876546] was not delivered. [10] dead letters encountered, no more dead letters will be logged. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [05/25/2017 08:16:59.110] [Thread-0] [CoordinatedShutdown(akka://lifecycle)] Starting coordinated shutdown from JVM shutdown hook

Process finished with exit code 130 (interrupted by signal 2: SIGINT)

 */