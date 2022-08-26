package ru.job4j.tracker;

public class AddManyApplication implements UserAction {

    private final Output out;

    public AddManyApplication(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "ADD MANY APPLICATION";
    }

    @Override
    public boolean execute(Input input, Store store) {
        System.out.println("=== ADD MANY APPLICATION ===");
        String name = input.askStr("Number of applications: ");
        for (int i = 1; i <= Integer.parseInt(name); i++) {
            Item item = new Item(Integer.toString(i));
            store.add(item);
        }
        System.out.println("Заявки добавлены");
        return true;
    }
}
