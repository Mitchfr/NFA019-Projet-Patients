package Cabinet_Patients;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class lecture {
	/*
	 * Methode qui lit les informations du fichier 'passé en paramêtre 
	 */
	public static <arrayUtilisateur> ArrayList<Patients> litFichierPatients(ArrayList<Patients> arrayUtilisateur, String NomFichier) {
		
		Patients utilisateur;								// instanciation de l'objet utilisateur		
		int memID;				
		 File fichier = null;
		 FileReader fr = null;
		 BufferedReader br = null;
		 	 
		 /*
		  * Lecture du fichier 'patient.txt'
		  */
		 try {
			fichier = new File (NomFichier);
			fr = new FileReader (fichier);
			br = new BufferedReader(fr);
			String line;
		if (NomFichier=="patient.txt") {
			// Boucle de lecture du fichier
			while((line=br.readLine())!=null)
			{	
				// splitArray sert à séparer les iformation sur 1 ligne (id, non,prenom,etc..)
				String[] splitArray = null;			
				splitArray = line.split(",");
				
				// on mets les infos lues dans l'array 'utilisateur'
				
				int verif = Integer.parseInt(splitArray[1]);
				if (verif!=0) {					//Si le patient est supprimé on ne l'affiche pas			
					utilisateur =new Patients();
					utilisateur.setID(Integer.parseInt(splitArray[0]));
					utilisateur.setPatientIsDel(Integer.parseInt(splitArray[1]));
					utilisateur.setNom(splitArray[2]);
					utilisateur.setPrenom(splitArray[3]);
					utilisateur.setTextSexe(splitArray[4]);
					utilisateur.setNSS(splitArray[5]) ;	
					utilisateur.setDateNaissance(splitArray[6]);
					utilisateur.setDateEntree(splitArray[7]);
					utilisateur.setDateSortie(splitArray[8]);
					utilisateur.setTelephone(splitArray[9]);
					arrayUtilisateur.add(utilisateur);
					memID= utilisateur.setID(Integer.parseInt(splitArray[0]));				// on met le dernier ID du dernier enregistrement dans memID
				}
			}
					
		}
			
	}
		 catch (IOException ex){	
			 System.out.println(" Il y a une erreur de lecture du fichier");			// affichage message d'erreur
			 
		 }
		 
		return  arrayUtilisateur;
	
	}
	
	public static <arrayExamens> ArrayList<Examens> litFichierExamens(ArrayList<Examens> arrayExamens, String NomFichier) {
		
	
		Examens examens = null;
		
		try {
		 File fichier = null;
		 FileReader fr = null;
		 BufferedReader br = null;
		 
		 
		 /*
		  * Lecture du fichier 'examens.txt'
		  */
			fichier = new File (NomFichier);
			fr = new FileReader (fichier);
			br = new BufferedReader(fr);
			String line;
	
	
		if (NomFichier=="examens.txt") {
			// Boucle de lecture du fichier
			while((line=br.readLine())!=null)
			{
	
				String[] splitArray = null;
				
				splitArray = line.split(",");
				
				// on mets les infos lues dans l'array 'utilisateur'
				int verif=Integer.parseInt(splitArray[2]);
				
				if (verif!=0) {											//Si setExamIsDel est different de 0 on affiche sinon, non
					examens =new Examens();
					examens.setId_Patient_Exam(Integer.parseInt(splitArray[0]));
					//examens.memID;
					examens.setId_Patient(Integer.parseInt(splitArray[1]));		
					examens.setExamIsDel(Integer.parseInt(splitArray[2]));
					Object type= splitArray[3];
					examens.setTypeExamens(type);								
					examens.setDateExamens(splitArray[4]);
					
					arrayExamens.add(examens);
				}
			}			
		}	
			
	}
	 catch (IOException ex){	
		 System.out.println(" Il y a une erreur de lecture du fichier");	// message d'erreur si probleme sur le fichier		 
	}		 
	return  arrayExamens;					// retourne l'arraList 'arrayExamens'
  }
}