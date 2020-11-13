package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}
        
        

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
        
                       
	public void entreAuGarage(Garage g) throws Exception {
		if((!myStationnements.isEmpty()) && (myStationnements.get(myStationnements.size()-1).estEnCours())){
                    throw new Exception("La voiture est deja dans un garage");
                }
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
            if (estDansUnGarage()){
                myStationnements.get(myStationnements.size()-1).terminer();
            }else{
                throw new Exception("la voiture n'est pas dans un garage");
            }
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
            Set<Garage> liste = new HashSet<>();
            for(Stationnement s : myStationnements ){
                liste.add(s.getGarage());
            }
            return liste;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
            
            if(myStationnements.isEmpty()){
                return false;
            }
            return myStationnements.get(myStationnements.size()-1).estEnCours();
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
            
            List<Stationnement> liste = new LinkedList<>(myStationnements);
            
            for(int i =0; i<liste.size();i++){
                String garage = liste.get(i).getGarage().toString();
                
                out.append(garage+"\n");
                out.append(liste.get(i).toString()+"\n");
                
                for(int j = i; j<liste.size(); j++){
                    if(liste.get(j).getGarage()==liste.get(i).getGarage()){
                        out.append(liste.get(j).toString()+"\n");
                        liste.remove(liste.get(j));
                    }
                }
                liste.remove(i);
            }
	}

}
