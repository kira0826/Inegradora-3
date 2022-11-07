package neotunes.ui;

import neotunes.model.*;

import java.util.Scanner;

public class NeoTunesManager {

    static Scanner scanner;
    static NeotunesController controller;


    public static void main(String[] args) {

        initValues();
        boolean continueOnLoop = true;

        while (continueOnLoop){

            displayMenu();
            String menuSelection = scanner.nextLine();
            switch (menuSelection){

                case "1":
                    registerUserProducer();
                    break;
                case "2":
                    registerUserConsumer();
                    break;
                case "3":
                    registerSong();
                    break;
                case "4":
                    registerPodcast();
                    break;
                case "5":
                    createPlaylist();
                    break;
                case "0":
                    continueOnLoop = false;
                    System.out.println("Hasta luego usuario.");
                    break;
                default:
                    System.out.println("El número dado no coincide con las opciones brindadas por el menú.");
                    break;

            }
        }

    }

    /**
     * This method initialize the scanner object, useful to read the inputs done by the user.
     */
    public static void initValues() {
        scanner = new Scanner(System.in);
        controller = new NeotunesController();
    }

    /**
     * This method storages and display the structure of the program menu.
     */
    public static void displayMenu(){
        String menu = """
                Bienvenido usuario,  por favor escriba el número que correponde con la operación que desea realizar:
                
                1. Registrar usuario productor.
                2. Registrar usuario consumidor.
                3. Registrar canción.
                4. Registrar podcast.
                5. Crear lista de reproducción.
                0. Salir del pograma.""";
        System.out.println(menu);
    }


    /**
     * This method solicits the necessary information in order to create a user  producer. Depending on the user producer type
     * it will ask certain data.
     */
    public static void registerUserProducer(){

        System.out.println("Dame el nickname:");
        String nickname = scanner.nextLine();
        if (!controller.isNicknameUnique(nickname)){
            for (;!controller.isNicknameUnique(nickname);){
                System.out.println("El nickname dado ya está usado, dame otro:");
                nickname = scanner.nextLine();
            }
        }
        System.out.println("Dame el ID del nuevo usario: ");
        String id = scanner.nextLine();
        if (!controller.isIdUnique(id)){
            for (;!controller.isIdUnique(id);){
                System.out.println("El id dado ya está usado, dame otro:");
                id = scanner.nextLine();
            }
        }

        System.out.println("Dame el url de la foto de perfil de este nuevo usuario: ");
        String url = scanner.nextLine();

        System.out.println("Dame la fecha de vinculación en el formato yyyy-mm-dd");
        String dateOfLinking = scanner.nextLine();
        System.out.println("Escribe 1 si el usario es un artista, escribe 2 si el usuario es un creador de contenido");
        String type = scanner.nextLine();

        switch (type){

            case "1":
                System.out.println(controller.registerArtist(nickname,id,dateOfLinking,url));
                break;
            case "2":
                System.out.println(controller.registerContentCreator(nickname,id,dateOfLinking,url));
                break;
        }

    }
    /**
     * This method solicits the necessary information in order to create a user consumer. Depending on the user consumer type
     * it will ask certain data.
     */
    public static void registerUserConsumer(){
        System.out.println("Dame el nickname:");
        String nickname = scanner.nextLine();
        if (!controller.isNicknameUnique(nickname)){
            for (;!controller.isNicknameUnique(nickname);){
                System.out.println("El nickname dado ya está usado, dame otro:");
                nickname = scanner.nextLine();
            }
        }
        System.out.println("Dame el ID del nuevo usario: ");
        String id = scanner.nextLine();
        if (!controller.isIdUnique(id)){
            for (;!controller.isIdUnique(id);){
                System.out.println("El id dado ya está usado, dame otro:");
                id = scanner.nextLine();
            }
        }
        System.out.println("Dame la fecha de vinculación en el formato yyyy-mm-dd");
        String dateOfLinking = scanner.nextLine();
        System.out.println("Escribe 1 si el usario es de tipo STANDARD, escribe 2 si el usuario es PREMIUM");
        String type = scanner.nextLine();

        switch (type){

            case "1":
                System.out.println(controller.registerStandardUser(nickname,id,dateOfLinking));
                break;
            case "2":
                System.out.println(controller.registerPremiumUser(nickname,id,dateOfLinking));
                break;
        }


    }


    /**
     * This method solicits the necessary information in order to create a Song. It is worth to say that must be previously created
     * at least one Artist to operate this code.
     */
    public static void registerSong(){
        if (controller.isThereArtist()){

            System.out.println("Dame el nombre de la canción:");
            String name = scanner.nextLine();

            System.out.println("Dame la url de la portada:");
            String urlCover = scanner.nextLine();

            System.out.println("Dame la duración de la canción, si supera el minuto por favor seguir el formato de ejemplo: 5:10");
            String duration = scanner.nextLine();

            System.out.println("Dame el precio de la canción:");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Por favor escriba el número que corresponde con el autor de la canción: ");
            System.out.println(controller.getArtistInfo());
            int artistPosition = Integer.parseInt(scanner.nextLine());

            System.out.println("Por favor escriba el número que corresponde con el género de la canción: ");
            System.out.println(controller.getGenres());
            int genrePosition = Integer.parseInt(scanner.nextLine());

            System.out.println("Dame el nombre del album de la canción:");
            String album = scanner.nextLine();
            System.out.println(controller.registerSong(name,urlCover,duration,artistPosition,price,album,genrePosition));
        }else System.out.println("No hay artistas por el momento, por tanto no se puede realizar esta acción.");


    }
    /**
     * This method solicits the necessary information in order to create a Podcast. It is worth to say that must be previously created
     * at least one ContentCreator to operate this code.
     */
    public static void registerPodcast(){
        if (controller.isThereContentCreators()){
            System.out.println("Dame el nombre del poscast:");
            String name = scanner.nextLine();

            System.out.println("Dame la url de la portada:");
            String urlCover = scanner.nextLine();

            System.out.println("Dame la duración del podcast, si supera el minuto por favor seguir el formato de ejemplo: 5:10");
            String duration = scanner.nextLine();

            System.out.println("Por favor escriba el número que corresponde con el autor del podcast: ");
            System.out.println(controller.getContentCreatorInfo());
            int contentCreatorPosition = Integer.parseInt(scanner.nextLine());

            System.out.println("Por favor escriba el número que corresponde con la categoría del podcast: ");
            System.out.println(controller.getCategories());
            int categoriesPosition = Integer.parseInt(scanner.nextLine());

            System.out.println("Dame la descripción del podcast:");
            String description = scanner.nextLine();

            System.out.println(controller.registerPodcast(name,urlCover,duration,contentCreatorPosition,description,categoriesPosition));

        }else System.out.println("No hay creadores de contenido, por tanto no se puede realizar esta acción.");
    }

    public static void createPlaylist(){

        if (controller.isThereConsumers()){
            System.out.println("Dame el nombre de la playlist");
            String name = scanner.nextLine();
            System.out.println("Escribe el nombre que correponde con el usuario consumidor al cual deseas agregar la playlist:");
            System.out.println(controller.getUserConsumerInfo());
            int consumerPosition = Integer.parseInt(scanner.nextLine());
            System.out.println(controller.createPlaylist(name,consumerPosition));
        }else System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta operación.");


    }




}