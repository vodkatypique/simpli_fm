package projet_poo;

abstract class Structure {
    private final String dateCreation;

    Structure(String date) {
        this.dateCreation = date;
    }

    @Override
    public String toString() {
        return "Structure qui date de " + this.dateCreation;
    }
}
