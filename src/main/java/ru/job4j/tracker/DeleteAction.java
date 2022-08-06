package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        int id = input.askInt("Enter id: ");
        if (store.delete(id)) {
            out.println("Заявка удалена успешно.");
        } else {
            out.println("Гшибка удаления заявки");
        }
        return true;
    }
}
