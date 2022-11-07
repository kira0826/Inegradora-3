package neotunes.model.audio;

public enum Genre {

    ROCK,
    POP,
    TRAP,
    HOUSE;


    /**This method concatenates the genre literals.
     * @return A string with the genre info and their position.
     */
    public static String concatenateLiterals(){
        String message = "Géneros: "  + "\n";
        for(int i = 0; i < values().length;i++ ){
            message += i+") " + values()[i].toString() +"." + "\n";
        }
        return message;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
