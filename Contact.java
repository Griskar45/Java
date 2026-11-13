public record Contact (String name) {
    public Contact {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Нужно что-то ввести");
        }
    }
}
