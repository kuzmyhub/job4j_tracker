package ru.job4j.tracker;

public class DeleteManyApplication implements UserAction {

    private final Output out;

    public DeleteManyApplication(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "DELETE MANY APPLICATION";
    }

    @Override
    public boolean execute(Input input, Store store) {
        System.out.println("=== DELETE MANY APPLICATION ===");
        String name = input.askStr("Number of applications: ");
        for (int i = 0; i <= Integer.parseInt(name); i++) {
            store.delete(i);
        }
        System.out.println("Заявки удалены");
        return true;
    }
}
