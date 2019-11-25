package projet_poo;

abstract class Structure {
    private final String date_creation;

    Structure(String date) {
        this.date_creation = date;
    }

    @Override
    public String toString() {
        return "Structure qui date de " + this.date_creation;
    }
}
