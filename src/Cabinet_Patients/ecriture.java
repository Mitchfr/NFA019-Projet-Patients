package Cabinet_Patients;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;

public class ecriture {
	
	/*
	 * Methode qui enregiste les informations du fichier 'patient.txt' contenue dans la Jlist passée en paramêtre 
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void EcriturePatients(ArrayList<Patients> arrayUtilisateur, JList listPatients  ) throws IOException {
	/*
	 * Enregistrement des données de l'objet 'utilisateur'
	 * 
	 */
		File f=new File("patient.txt");								// initialisation du fichier
		FileWriter fw = new FileWriter(f,false);					
		BufferedWriter buffer = new  BufferedWriter(fw);			//initialisation du buffer qui accelerera la lecture
		try {
					
			/*
			 * boucle qui parcours la JList listPatients 
			 * et ecrit les informations dans le fichier
			 */
			for (int i = 0;i<listPatients.getModel().getSize();i++) {
				buffer.write(listPatients.getModel().getElementAt(i).toString());
				buffer.newLine();
				buffer.flush();

			}
		
		}catch (IOException ex) {
			System.out.println("Il a problème sur le fichier d'enregistrement");		// message d'erreur si problème avec le fichier
		}
		finally {
			buffer.close();						// ferme le buffer
			fw.close();							// ferme le fichier
		}


	}
	
	/*
	 * Methode qui enregiste les informations du fichier 'examens.txt' contenue dans la Jlist passée en paramêtre 
	 */
	public static void EcritureExamens(ArrayList<Examens> arrayExamens) throws IOException {
	/*
	 * Enregistrement des données de l'objet 'utilisateur'
	 * 
	 */
		File f=new File("examens.txt");							// initialisation du fichier
		FileWriter fw = new FileWriter(f,false);
		BufferedWriter buffer = new  BufferedWriter(fw);		//initialisation du buffer qui accelerera la lecture			
		try {
			
			/*
			 * boucle qui parcours l'arrayList 'arrayExamens' 
			 * et ecrit les informations dans le fichier
			 */
			for (int i = 0;i<arrayExamens.size();i++) {
				buffer.write(arrayExamens.get(i).toString());
				buffer.newLine();
				buffer.flush();

			}
		
		}catch (IOException ex) {
			System.out.println("Il a problème sur le fichier d'enregistrement");		// message d'erreur si problème avec le fichier
		}
		finally {
			buffer.close();
		}
	}
	
}