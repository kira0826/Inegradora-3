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
                case "6":
                    addAudioToPlaylist();
                    break;
                case "7":
                    deleteAudioInplaylist();
                    break;
                case "8":
                    sharePlaylist();
                    break;
                case"9":
                    simulateReproduction();
                    break;
                case "10":
                    purchaseSellable();
                    break;
                case "11":
                    totalReproductions();
                    break;
                case "12":
                    topSellingSong();
                    break;
                case "13":
                    favoriteSongGenre();
                    break;
                case"14":
                    favoritePodcastCategory();
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
                6. Añadir audio a una playlist.
                7. Eliminar audio de una playlist.
                8. Compartir una playlist.
                9. Simular reproducción.
                10. Comprar canción.
                11. Calcular el total de reproducciones de un tipo de audio. 
                12. Canción más vendida
                13. Género favorito de canciones.
                14. Categoría favorita de podcast.
                0. Salir del pograma.""";
        System.out.println(menu);
    }


    /**
     * This method solicits the necessary information in order to create a user  producer. Depending on the user producer type
     * it will ask certain data.
     */
    public static void registerUserProducer(){

        System.out.println("Dame el nickname: ");
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

    /**
     * This method solicits the information in order to create a playlist for a specific consumer.
     */
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
    /**
     * This method solicits the information in order to add an audio into a playlist for a specific consumer.
     */

    public static void addAudioToPlaylist (){
        if (controller.isThereConsumers() && controller.isThereAudios()){
            System.out.println("Por favor escoje el usuario al cual deseas agregar un audio a una de sus playlist:");
            System.out.println(controller.getUserConsumerInfo());
            int consumerIndex = Integer.parseInt(scanner.nextLine());
            if (controller.isTherePlaylist(consumerIndex)){
                System.out.println("Por favor escribe el número de  la playlist a la cual deseas agregar el audio.");
                System.out.println(controller.getUserPlaylistInfo(consumerIndex));
                int playlistIndex = Integer.parseInt(scanner.nextLine());
                System.out.println("Por favor escribe el número del audio que deseas agregar a la Playlist: ");
                System.out.println(controller.concatenateAudiosInfo());
                int audioIndex = Integer.parseInt(scanner.nextLine());

                if (controller.addAudio(audioIndex,consumerIndex,playlistIndex)){
                    System.out.println("Audio añadido.");
                }else System.out.println("No se pudo agregar la canción.");

            }else System.out.println("El usuario no tiene playlist registradas, por tanto, no se puede realizar esta acción.");
        }else {
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereConsumers()) System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta acción.");
        }


    }

    /**
     * This method solicits the information in order to delete an audio from a playlist of a specific consumer.
     */
    public static void deleteAudioInplaylist(){
        if (controller.isThereConsumers() && controller.isThereAudios()){
            System.out.println("Por favor escoje el usuario al cual deseas borrar un audio en una de sus playlist:");
            System.out.println(controller.getUserConsumerInfo());
            int consumerIndex = Integer.parseInt(scanner.nextLine());
            if (controller.isTherePlaylist(consumerIndex)){
                System.out.println("Por favor escribe el número de  la playlist a la cual deseas borrar un audio.");
                System.out.println(controller.getUserPlaylistInfo(consumerIndex));
                int playlistIndex = Integer.parseInt(scanner.nextLine());

                if (controller.isThereAudiosInPlaylist(consumerIndex,playlistIndex)){
                    System.out.println("Por favor escribe el número que corresponde con el audio que deseas eliminar:");
                    System.out.println(controller.concatenateAudioFromPlaylist(consumerIndex,playlistIndex));
                    int audioPosition = Integer.parseInt(scanner.nextLine());

                    if (controller.deleteAudio(audioPosition,consumerIndex,playlistIndex)){
                        System.out.println("Audio eliminado.");
                    }else System.out.println("No se pudo eliminar el audio.");
                }else System.out.println("La playlist elegida no posee audios, por tanto no se puede realizar esta acción.");


        }else System.out.println("El usuario escogido no posee playlist.");
    }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereConsumers()) System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta acción.");
        }
    }
    /**
     * This method solicits the information in order to share a playlist from a specific consumer.
     */
    public static void sharePlaylist(){
        if (controller.isThereConsumers() && controller.isThereAudios()){
            System.out.println("Por favor escoje el usuario que desea compartir una playlist:");
            System.out.println(controller.getUserConsumerInfo());
            int userIndex = Integer.parseInt(scanner.nextLine());
            if (controller.isTherePlaylist(userIndex)){
                System.out.println("Por favor escribe el número de  la playlist que deseas compartir.");
                System.out.println(controller.getUserPlaylistInfo(userIndex));
                int playlistIndex = Integer.parseInt(scanner.nextLine());

                if (controller.isThereAudiosInPlaylist(userIndex,playlistIndex)){

                    System.out.println("Share code: " + controller.sharePlaylist(userIndex,playlistIndex));
                }else System.out.println("La playlist elegida no posee audios, por tanto no se puede realizar esta acción.");


            }else System.out.println("El usuario escogido no posee playlist.");
        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereConsumers()) System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta acción.");
        }
    }
    /**
     * This method solicits the necessary information in order to simulate the reproduction of an audio by a specific consumer.
     */
    public static void simulateReproduction(){

        if (controller.isThereConsumers() && controller.isThereAudios()){
            System.out.println("Por favor escoje el usuario que desea simular la reproducción de un audio");
            System.out.println(controller.getUserConsumerInfo());
            int userIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Por favor escribe el número del audio que deseas reproducir.");
            System.out.println(controller.concatenateAudiosInfo());
            int audioIndex = Integer.parseInt(scanner.nextLine());

            System.out.println(controller.reproduceAudio(userIndex,audioIndex));

        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereConsumers()) System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta acción.");
        }

    }
    /**
     * This method solicits the necessary information in order to purchase a Sellable object from a specific user consumer.
     */
    public static void purchaseSellable(){
        if (controller.isThereConsumers() && controller.isThereSellables()){
            System.out.println("Por favor escoje el usuario que desea simular la reproducción de un audio");
            System.out.println(controller.getUserConsumerInfo());
            int userIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Por favor escribe el número del vendible que deseas comprar.");
            System.out.println(controller.concatenateSellableInfo());
            int sellableIndex = Integer.parseInt(scanner.nextLine());

            if (controller.purchaseSellable(userIndex,sellableIndex)){
                System.out.println("Compra exitosa.");
            }else System.out.println("No se pudo realizar la compra");

        }else{
            if (!controller.isThereSellables()) System.out.println("No hay vendibles registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereConsumers()) System.out.println("No hay consumidores registrados, por tanto no se puede realizar esta acción.");
        }
    }

    /**
     * This method asks for the audio type that will be used to count the number of reproductions.
     */
    public static void totalReproductions(){
        if (controller.isThereAudios()){
            System.out.println("Escribe 1 si deseas contabilizar las reproducciones de las canciones,  escribe 2 si es sobre los podcast");
            String selection = scanner.nextLine();
            switch (selection){

                case "1":
                    if (controller.isThereSong()){
                        System.out.println(controller.songsReproductions());
                    }else System.out.println("No hay canciones registradas, por tanto no se puede realizar esta acción.");
                    break;
                case "2":
                    if (controller.isTherePodcast()){
                        System.out.println(controller.podcastReproductions());
                    }else System.out.println("No hay podcast registradas, por tanto no se puede realizar esta acción.");
                    break;
                default:
                    System.out.println("La opción nc coincide con el menú.");
            }
        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");

        }
    }

    /**
     * This method is used to call the topSellingSong controller method if there are audios and songs.
     */
    public static void topSellingSong(){

        if (controller.isThereAudios() && controller.isThereSong()){

            System.out.println(controller.topSellingSong());
        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereSong()) System.out.println("No hay canciones registradas, por tanto no se puede realizar esta acción.");
        }
    }

    /**
     * This method is used to identify which operation must the system do: identify the favorite song genre of the program,
     * or the favorite song genre of a specific user.
     */
    public static void favoriteSongGenre(){
        if (controller.isThereAudios() && controller.isThereSong()){

            System.out.println("Escriba 1 si desea saber el género de canción  favorito  de un usuario en específico, escriba 2 si desea saber el de todo el programa. ");
            String selection = scanner.nextLine();

            switch (selection){
                case "1":
                    if (controller.isThereConsumers()) {
                        System.out.println("Por favor escoje el usuario al cual deseas saber su género favorito de canciones.");
                        System.out.println(controller.getUserConsumerInfo());
                        int userPosition = Integer.parseInt(scanner.nextLine());
                        System.out.println(controller.favoriteUserSongGenre(userPosition));
                    }else System.out.println("No hay usuarios registrados por tanto no se puede realizar esta operación.");
                    break;
                case "2":
                    System.out.println(controller.favoriteGlobalSongGenre());
                    break;
            }
        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isThereSong()) System.out.println("No hay canciones registradas, por tanto no se puede realizar esta acción.");
        }
    }
    /**
     * This method is used to identify which operation must the system do: identify the favorite podcast category of the program,
     * or the favorite podcast category of a specific user.
     */
    public static void favoritePodcastCategory(){
        if (controller.isThereAudios() && controller.isTherePodcast()){

            System.out.println("Escriba 1 si desea saber la categoría de un podcast favorita  de un usuario en específico, escriba 2 si desea saber la de todo el programa. ");
            String selection = scanner.nextLine();

            switch (selection){
                case "1":
                    if (controller.isThereConsumers()) {
                        System.out.println("Por favor escoje el usuario al cual deseas saber su categoría favorita de podcast.");
                        System.out.println(controller.getUserConsumerInfo());
                        int userPosition = Integer.parseInt(scanner.nextLine());
                        System.out.println(controller.favoriteUserPodcastCategory(userPosition));
                    }else System.out.println("No hay usuarios registrados por tanto no se puede realizar esta operación.");
                    break;
                case "2":
                    System.out.println(controller.favoriteGlobalPodcastCategory());
                    break;
            }
        }else{
            if (!controller.isThereAudios()) System.out.println("No hay audios registrados por tanto no se puede realizar esta acción.");
            if (!controller.isTherePodcast()) System.out.println("No hay podcasts registrados, por tanto no se puede realizar esta acción.");
        }
    }
}