import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dispatcher implements Runnable{
    private MainMemory memory;
    private Scheduler scheduler;
    private PriorityQueue<PCB> readyProcesses; // All new readyProcesses go here
    private PriorityQueue<PCB> otherProcesses; // All new readyProcesses go here
    private int pause = 0;

    @Override
    public void run() {
        this.start();
    }

    public Dispatcher(CPU cpu, MainMemory memory) {
        this.memory = memory;
        this.readyProcesses = new PriorityQueue<PCB>();
        this.scheduler = new Scheduler(memory, "Scheduler 1");
    }

    public void addProcess(PCB process) {
    }

    public String displayProcesses() {
        Iterator it = readyProcesses.iterator();
        String output = "";
        if (readyProcesses.isEmpty()) {
            return "No readyProcesses loaded";
        } else {
            while (it.hasNext()) {
                output = output + it.next().toString();
            }
            return "Displaying all readyProcesses:\n" + output;
        }
    }

    public void dispatch(PCB process) {
        switch (process.getState()) {
            // NEW
            case 0:
                LinkedList<MainMemory.Page> pagesUsed = memory.allocateMemory(process.getMemoryRequirement());
                if (pagesUsed.size() > 0) {
                    process.setState(1);
                    process.setPagesUsed(pagesUsed);
                    readyProcesses.add(process);
                } else {
                    process.setPriority(process.getPriority() - 1);
                    otherProcesses.add(process);
                    // add to whatever queue
                }
                ;
                break;

            // READY
            case 1:
                break;

            // RUNNING
            case 2:
                break;

            // WAITING/BLOCKED
            case 3:
                break;

            // TERMINATING
            case 4:
                memory.deallocateMemory(process.getMemoryRequirement(), process.getPid());
                break;
        }
    }

    public String start(int n) {
        scheduler.setPauseCycle(n);
        for (PCB readyProcess : this.readyProcesses) {
            scheduler.start(readyProcess);
        }
        scheduler.finish();
        return "Done";
    }

    public String start() {
        for (PCB readyProcess : this.readyProcesses) {
            scheduler.start(readyProcess);
        }
        scheduler.finish();
        return "Done";
    }

    public int getAmount() {
        return readyProcesses.size();
    }


}