package com.sailfish.akka.route;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

/**
 * @author sailfish
 * @create 2017-05-25-上午10:02
 */
public class WatchActor extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Router router;

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef work = getContext().actorOf(Props.create(MyWork.class), "worker" + i);
            getContext().watch(work);
            routees.add(new ActorRefRoutee(work));
        }
        //Router的一种策略
        router = new Router(new RoundRobinRoutingLogic(), routees);

    }

    @Override
    public void onReceive(Object msg) throws Throwable {

        if (msg instanceof MyWork.Msg) {

            router.route(msg, getSender());
        } else if (msg instanceof MyWork.Msg) {
            router = router.removeRoutee(((Terminated) msg).actor());
            System.out.println(((Terminated) msg).actor().path() + " is close, routees=" + router.routees().size());

            if (router.routees().size() == 0) {

                System.out.println("close system");
                RouterMain.flag.send(false);
                //  todo shutdown函数在这个版本中取消了？
                getContext().system();
            }
        } else {
            unhandled(msg);
        }
    }
}
