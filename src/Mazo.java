import java.util.ArrayList;
import java.util.Random;

public class Mazo {
     private ArrayList<Carta> conjCartas = new ArrayList<Carta>();
     private ArrayList<String> atributosCarta = new ArrayList<String>();

    Mazo(int cantidad, String... atributos){
        for (String atrib: atributos) {
            atributosCarta.add(atrib);
        }
        Random randomGenerator = new Random();
        for (int j=0; j < cantidad; j++){ //cantidad de cartas
            String nombredecarta = "carta "+j;
            Carta cartaNueva = new Carta("carta "+j);
            for(int i=0; i < atributosCarta.size(); i++) { //cantidad de atributos de la carta
                cartaNueva.setAtributo(atributosCarta.get(i), randomGenerator.nextInt(100));
            }
            conjCartas.add(cartaNueva);
        }
    }

    public void printCartas(){
        for(int i=0; i < conjCartas.size(); i++){
            System.out.print(conjCartas.get(i).getNombreCarta()+" ");
            conjCartas.get(i).printAtributos();
            System.out.print("\n");
        }
    }

    public void repartirCartas(Jugador... jugadores){
        int cantCartasParaCada = conjCartas.size() / jugadores.length;
        int inicio = 0;
        for (Jugador jug: jugadores){
            jug.agregarMazo(new ArrayList<Carta>(conjCartas.subList(inicio,cantCartasParaCada)));
            inicio += cantCartasParaCada;
            cantCartasParaCada += cantCartasParaCada;
        }
    }

    public Boolean tieneCartasValidas(){
        if (!conjCartas.isEmpty()) {
            Carta comparadora = conjCartas.get(0);
            ArrayList<String> atribsOriginales = comparadora.getListaAtributos();

            for (int i = 1; i < conjCartas.size(); i++) { //por cada carta del mazo
                ArrayList<String> atribsCartaAux = conjCartas.get(i).getListaAtributos();
                if ( atribsCartaAux.size() == atribsOriginales.size()) { //si tienen la misma cant de atribs
                    for (int j = 0; j < atribsOriginales.size(); j++) { //por cada atrib
                        if (atribsCartaAux.get(j) != atribsOriginales.get(j)){
                            return false;
                        }
                    }
                }else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
