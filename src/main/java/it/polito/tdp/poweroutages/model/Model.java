package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Poweroutages> pr;
	List<Poweroutages> worst;
	
	Integer personeCoinvolte=0;
	
	int count=0;
	
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Poweroutages> getPr(Nerc n) {
		return podao.getPrList(n);
	}

	


	public List<Poweroutages> trovaSequenza(Nerc n,int y, int h) {
		List<Poweroutages> parziale=new ArrayList<Poweroutages>();
		
		this.worst=new ArrayList<>();
		personeCoinvolte=0;
		 
		
		podao=new PowerOutageDAO();
		pr=podao.getPrList(n);
	    
		Collections.sort(pr);
	    
	    cercaSequenza( parziale, y,h);
		
		
		return worst;
	}

	private void cercaSequenza(List<Poweroutages> parziale, int years,int hours) {
		// TODO Auto-generated method stub
		
		//aggiorno la soluzione migliore
		int persone=calcolaPersone(parziale);
	    //if() se parziale più persone coinvolte di worst
	    //aggiorno worst
		if(worst==null || persone>personeCoinvolte) {
				worst=new ArrayList<>(parziale);
				personeCoinvolte=persone;
		}
		
		for(Poweroutages p: pr) {
				//se non contiene evento lo aggiungo
				if(!parziale.contains(p)) {
					parziale.add(p);
				
		
			//se è valida allora salgo di livello nella ricorsione
		     if(valida(parziale,years,hours)) {
				cercaSequenza(parziale,years,hours);
		     }
			
		      parziale.remove(p);
		}
		}
	}
	
		
	public int calcolaPersone(List<Poweroutages> prList) {
		// TODO Auto-generated method stub
		int somma=0;
		for(Poweroutages p: prList) {
			somma+=p.getCustomers_affected();
		}
		return somma;
	}	
	


	private boolean valida(List<Poweroutages> parziale,int y,int h) {
		// TODO Auto-generated method stub
		//calcolo le ore di parziale+ore di p, se supera restituisco false
		if(calcolaOre(parziale)>h) {
			return false;
		}
		//calcolo gli anni, se supera restituisco false
		if(!calcolaAnni(parziale,y)) {
			return false;
		}
		
		return true;
	}
	
	public int calcolaOre(List<Poweroutages> parziale) {
		// TODO Auto-generated method stub
		int ore=0;
		for(Poweroutages pr:parziale) {
			ore+=pr.calcolaOre();
		}
		
		return ore;
	}
	
	private boolean calcolaAnni(List<Poweroutages> parziale,int y) {
		// TODO Auto-generated method stub
		if(parziale.size()>2) {
			int min=parziale.get(0).getYear();
			int max=parziale.get(parziale.size()-1).getYear();
			if((max-min)>y) {
				return false;
			}
		}
		
		
		return true;
	}

	

	
	
	
}
