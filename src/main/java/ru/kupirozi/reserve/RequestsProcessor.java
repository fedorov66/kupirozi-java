package ru.kupirozi.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.db.ItemQuantityHandler;
import ru.kupirozi.db.ReserveItemHandler;
import ru.kupirozi.db.StorageManager;

import java.util.*;

/**
 * Created by fedorov on 18.01.2018.
 */
public class RequestsProcessor extends  Thread {

    private static Logger log = LoggerFactory.getLogger(RequestsProcessor.class);

    public enum ProcessorState {
        PROCESS,
        AWAIT
    }

    private static StorageManager storage;

    private static ProcessorState state = ProcessorState.AWAIT;

    private static final List<ReserveRequest> requests = new ArrayList<ReserveRequest>();
    private static final Map<String, ReserveRequest> processed = new HashMap<String, ReserveRequest>();

    public RequestsProcessor(final StorageManager storage) {
        this.storage = storage;
    }

    private synchronized void processRequests() {
        log.debug("exec processRequests()");

        if (!processed.isEmpty()) {
            long now = new Date().getTime();
            for (String key : processed.keySet()) {
                ReserveRequest r = processed.get(key);
                    if ((now - r.getTimestamp()) > 300000) {
                    log.debug("remove proccessed by expiration:" + key);
                    processed.remove(key);
                }
            }
            log.debug("processed requests:" + processed.size());
        }

        if (!requests.isEmpty()) {
            for (Iterator<ReserveRequest> it = requests.iterator(); it.hasNext();) {
                ReserveRequest r = it.next();
                if (r.getStatus() == RequestStatus.NEW) {
                    log.info("request processing: " + r.getUid());
                    int total = (int)storage.execute(new ItemQuantityHandler(r.getItemId()));
                    r.setAvailableInDb(total);
                    if (r.getQuantity() <= total) {
                        storage.execute(new ReserveItemHandler(r));
                        total = total - r.getQuantity();
                        r.setStatus(RequestStatus.DONE);
                        r.setAvailable(total);
                    } else {
                        r.setStatus(RequestStatus.NOT_AVAILABLE);
                        r.setAvailable(total);
                    }
                } else if (r.getStatus() != RequestStatus.DONE && r.getStatus() != RequestStatus.NOT_AVAILABLE) {
                    r.setStatus(RequestStatus.FAIL);
                }
                if (r.getStatus() != RequestStatus.NEW) {
                    processed.put(r.getUid(), r);
                    it.remove();
                    synchronized (r) {
                        log.debug("proccessed notifyall");
                        r.notifyAll();
                    }
                }
            }
        }

        if (requests.isEmpty()) {
            state = ProcessorState.AWAIT;
            try {
                log.debug("all proccessed waiting...");
                wait();
            } catch (InterruptedException e) {
                /* noop */
            }
        }
    }

    public synchronized void addRequets(final ReserveRequest req) {
        requests.add(req);
        state = ProcessorState.PROCESS;
        log.debug("we have new one request:" + req.getUid());
        notify();
    }

    public List<ReserveRequest> getQueue() {
        return requests;
    }

    public Map<String, ReserveRequest> getProcessed() {
        return processed;
    }

    public boolean isReady(final String uid) {
        return processed.containsKey(uid);
    }

    public void run() {
        try {
            System.out.println("RequestsProcessor thread is running");
            while (true) {
                if (state == ProcessorState.PROCESS) {
                    System.out.println("process requests " + new Date());
                    processRequests();
                }
                sleep(300);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            System.out.println("RequestsProcessor Thread exiting"); // never called
        }
    }

}
