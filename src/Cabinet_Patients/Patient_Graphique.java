package Cabinet_Patients;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;


/*
 * @author Michel Mendi
 * 
 * Programme de gestion des Patients
 * Création, modification, suppression des Patients
 *  Création, modification, suppression des visites de Patients
 */

public class Patient_Graphique {


	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNSS;
	private JTextField textFieldDateNaissance;
	private JTextField textFieldDateEntree;
	private JTextField textFieldDateSortie;
	private JTextField textFieldTelephone;
	private JTextField textFieldPatientExam;
	private JTextField textSexe;
	private JFrame frmMash;
	private JTextField textFieldDateExam;
	private JTextField textFieldMessErreur;
	private JTextField comboBoxExamen;
	
	boolean saisieOk=true;
	public int ExamDel=0;

	public void setComboBoxExamen(JTextField comboBoxExamen) {
		this.comboBoxExamen = comboBoxExamen;
	}

	/*
	 * @param sexeMasculin : variable pour définir le sexe du Patient
	 * @param memID : permet de stocker l'ID du dernier enregistrement
	 * @param Patients : stocke les information d'un client
	 */
	public boolean sexeMasculin=true;
	int memID,memID_exam;							// variable pour mémoriser les ID's
	private ArrayList<Patients> arrayUtilisateur;
	Patients utilisateur;
	private ArrayList<Patients> arrayUtilisateur_temp;
	Patients utilisateur_temp;
	private ArrayList<Examens> arrayExamens;
	Examens examens;
	private ArrayList<Examens> arrayExamens_temp;
 
	/**
	 * Lancement de l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient_Graphique window = new Patient_Graphique();
					window.frmMash.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Création de l'application
	 */
	public Patient_Graphique() {
		initialize();
	}

	/**
	 * Initialise le contenue des fenetres
	 */
	private void initialize() {
								
		arrayUtilisateur = new ArrayList<Patients>();							// Sert à stocker les infos des Patients
		arrayExamens = new ArrayList<Examens>();								// Sert à stocker les infos des examens
		arrayExamens_temp = new ArrayList<Examens>();							// Arraylist temporaire pour stocker les examens correspondant à un patient sélectionné
		
		frmMash = new JFrame();
		frmMash.setForeground(new Color(143, 188, 143));
		frmMash.setTitle("M.A.S.H");
		frmMash.setBackground(new Color(143, 188, 143));
		frmMash.getContentPane().setBackground(new Color(143, 188, 143));
		
		JPanel panelPatient = new JPanel();										// zone d'affichage pour les patients
		panelPatient.setBackground(new Color(143, 188, 143));
		JLabel labelNom = new JLabel("Nom");									// Label Nom
		JLabel labelPrenom = new JLabel("Pr\u00E9nom");							// Label prénom
		JLabel labelNSS = new JLabel("NSS");									// Label Numéro de sécurité social
		JLabel labelDateNaiss = new JLabel("Date de Naissance");				// Label Date de naissance
		JLabel labelDateEntree = new JLabel("Date d'entr\u00E9e");				// Label Date d'entrée
		JLabel labelDateSortie = new JLabel("Date de sortie");					// Label Date de sortie
		JLabel labelTel = new JLabel("N\u00B0 de t\u00E9l\u00E9phone");			// Label Date de sortie
		
		/*
		 * Zone d'affichage des message d'erreurs
		 * on met un fond rouge pour attirer l'attention
		 */
		textFieldMessErreur = new JTextField();
		textFieldMessErreur.setForeground(new Color(0, 0, 0));
		textFieldMessErreur.setFont(new Font("Arial", Font.BOLD, 12));
		textFieldMessErreur.setBackground(new Color(173, 255, 47));
		textFieldMessErreur.setBounds(10, 334, 521, 20);
		frmMash.getContentPane().add(textFieldMessErreur);
		textFieldMessErreur.setColumns(10);
		
		textFieldMessErreur.setVisible(false);		
		textFieldNom = new JTextField();										// champs de saisie pour le nom
		textFieldNom.setBackground(new Color(240, 255, 240));
		textFieldPrenom = new JTextField();										// champs de saisie pour le prénom 	
		textFieldPrenom.setBackground(new Color(240, 255, 240));
		textFieldNSS = new JTextField();										// champs de saisie pour le n° de sécurité sociale
		textFieldNSS.setBackground(new Color(240, 255, 240));
		textFieldDateNaissance = new JTextField();								// champs de saisie pour la date de naissance
		textFieldDateNaissance.setBackground(new Color(240, 255, 240));
		textFieldDateEntree = new JTextField();									// champs de saisie pour la date d'entrée
		textFieldDateEntree.setBackground(new Color(240, 255, 240));
		textFieldDateSortie = new JTextField();									// champs de saisie pour le date de sortie
		textFieldDateSortie.setBackground(new Color(240, 255, 240));
		textFieldTelephone = new JTextField();									// champs de saisie pour le n° de téléphone	
		textFieldTelephone.setBackground(new Color(240, 255, 240));
		textFieldDateExam = new JTextField();									// champs de saisie pour le n° de date d'examens
		textFieldDateExam.setBackground(new Color(173, 216, 230));
	
		
		JLabel labelPhoto = new JLabel("");								// champs de saisie pour la photo	
		labelPhoto.setIcon(new ImageIcon("./mash.jpg"));
		ButtonGroup groupeBouttons = new ButtonGroup();							// Création d'un groupe de bouton ratio pour Masculin et Féminin
		JList listPatients = new JList();										// instanciation de 'listPatients' dans la Jlist
		listPatients.setBackground(new Color(240, 255, 240));
		JButton btnValider = new JButton("Ajouter");							// Création du boutton 'Valider'
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnValider.setForeground(new Color(0, 0, 0));
		btnValider.setBackground(new Color(222, 184, 135));
	
		
		
		/*
		 * Définition de la fenêtre principale
		 */
		frmMash.setBounds(100, 100, 1151, 670);
		frmMash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMash.getContentPane().setLayout(null);
		
		/*
		 * Définition de la fenêtre d'affichage des Patients
		 */
		panelPatient.setBounds(10, 38, 521, 303);
		frmMash.getContentPane().add(panelPatient);
		
		panelPatient.setBorder(BorderFactory.createTitledBorder("Ajouter un patient "));			
		panelPatient.setLayout(null);
		
		/*
		 * Définition du label 'Nom'
		 */
		labelNom.setBounds(10, 29, 137, 14);
		panelPatient.add(labelNom);
		
		/*
		 * Définition du label 'prénom'
		 */
		labelPrenom.setBounds(10, 54, 137, 14);
		panelPatient.add(labelPrenom);
		
		/*
		 * Définition du label 'N° de sécurité sociale'
		 */
		labelNSS.setBounds(10, 79, 137, 14);
		panelPatient.add(labelNSS);
		
		/*
		 * Définition du label 'date de naissance'
		 */
		labelDateNaiss.setBounds(10, 104, 137, 14);
		panelPatient.add(labelDateNaiss);
		
		/*
		 * Définition du label 'Date d'entrée'
		 */
		labelDateEntree.setBounds(10, 129, 137, 14);
		panelPatient.add(labelDateEntree);
		
		/*
		 * Définition du label 'Date de sortie'
		 */
		labelDateSortie.setBounds(10, 154, 137, 14);
		panelPatient.add(labelDateSortie);
		
		/*
		 * Définition du label 'N° de téléphone'
		 */
		labelTel.setBounds(10, 179, 137, 14);
		panelPatient.add(labelTel);

		/*
		 * Définition du champs 'Photo'
		 */
		labelPhoto.setBounds(352, 29, 159, 174);
		panelPatient.add(labelPhoto);
		
		
		/*
		 * Définition du champs 'Nom'
		 */
		textFieldNom.setBounds(157, 29, 185, 20);
		panelPatient.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		/*
		 * Définition du champs 'prénom'
		 */
		textFieldPrenom.setBounds(157, 54, 185, 20);
		panelPatient.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		/*
		 * Définition du champs 'N° de sécurité sociale'
		 */
		textFieldNSS.setBounds(157, 79, 185, 20);
		panelPatient.add(textFieldNSS);
		textFieldNSS.setColumns(10);
		
		/*
		 * Définition du champs 'Date de naissance'
		 */
		textFieldDateNaissance.setBounds(157, 104, 185, 20);
		panelPatient.add(textFieldDateNaissance);
		textFieldDateNaissance.setColumns(10);
		
		/*
		 * Définition du champs 'Date d'entrée'
		 */
		textFieldDateEntree.setBounds(157, 129, 185, 20);
		panelPatient.add(textFieldDateEntree);
		textFieldDateEntree.setColumns(10);
		
		/*
		 * Définition du champs 'Date de sortie'
		 */
		textFieldDateSortie.setBounds(157, 154, 185, 20);
		panelPatient.add(textFieldDateSortie);
		textFieldDateSortie.setColumns(10);
		
		/*
		 * Définition du champs 'N° de téléphone'
		 */
		textFieldTelephone.setBounds(157, 179, 185, 20);
		panelPatient.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);


		/*
		 * Ajout des boutons radios bttnMasculin et bttnFeminin dans le groupe 'groupeBouttons'
		 */
		JRadioButton bttnMasculin = new JRadioButton("Masculin");
		bttnMasculin.setBackground(new Color(143, 188, 143));
		JRadioButton bttnFeminin = new JRadioButton("Feminin");
		bttnFeminin.setBackground(new Color(143, 188, 143));
		
		/*
		 * Titre de l'application
		 */
	
		JLabel lblNewLabel = new JLabel("Application patient / examens");
		lblNewLabel.setBackground(new Color(143, 188, 143));
		lblNewLabel.setBounds(10, 0, 367, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frmMash.getContentPane().add(lblNewLabel);
		
		/*
		 * Onglet ajouter un examens
		 */
		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.setForeground(new Color(0, 0, 0));
		tabbedPanel.setBackground(new Color(143, 188, 143));
		tabbedPanel.setBounds(10, 359, 1115, 272);
		frmMash.getContentPane().add(tabbedPanel);
		
		/*
		 * cadre "ajouter patients"
		 */
		JLayeredPane panelAjout = new JLayeredPane();
		panelAjout.setForeground(new Color(0, 0, 0));
		panelAjout.setBackground(new Color(143, 188, 143));
		tabbedPanel.addTab("Ajouter examens", null, panelAjout, null);
		panelAjout.setLayout(null);
		
		/*
		 * Label Patient dans le cadre examens
		 */
		JLabel labelPatientExam = new JLabel("Patient");
		labelPatientExam.setBounds(10, 11, 109, 14);
		panelAjout.add(labelPatientExam);
		
		
		/*
		 * Zone de 'affichage pour le nom du patient
		 * pour l'examens.
		 * Ce champs est desactivé pour la saisie
		 */
		textFieldPatientExam = new JTextField();
		textFieldPatientExam.setBackground(new Color(173, 216, 230));
		textFieldPatientExam.setEditable(false);
		textFieldPatientExam.setBounds(129, 11, 224, 20);
		panelAjout.add(textFieldPatientExam);
		textFieldPatientExam.setColumns(10);
		
		/*
		 * Zone de saisie pour la date d'examens 
		 */
		textFieldDateExam.setBounds(129, 101, 138, 20);
		panelAjout.add(textFieldDateExam);
		textFieldDateExam.setColumns(10);
		
		/*
		 * Label pour la saisie date d'examens 
		 */
		JLabel labelDateExam = new JLabel("Date d'examen");
		labelDateExam.setBounds(10, 101, 109, 14);
		panelAjout.add(labelDateExam);

		/*
		 * Liste de choix pour les examens
		 */
		String [] comboExamen = {"Radio","Consultation","Echographie","Orthodonthie","Urologie","Cancerologie","Neurologie"};
		JComboBox comboBoxExamen = new JComboBox(comboExamen);
		comboBoxExamen.setBackground(new Color(173, 216, 230));
		comboBoxExamen.setBounds(129, 52, 214, 22);
		comboBoxExamen.setSelectedIndex(6);
		panelAjout.add(comboBoxExamen);
		
		/*
		 * 
		 * Label pour le type d'examens
		 */
		JLabel labelTypeExam = new JLabel("Type d'examen");
		labelTypeExam.setBounds(10, 53, 109, 14);
		panelAjout.add(labelTypeExam);

		/*
		 * Boutton d'ajout d'examens
		 */
		JButton btnValiderExamen = new JButton("Ajouter");
		btnValiderExamen.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnValiderExamen.setBackground(new Color(153, 204, 255));
		btnValiderExamen.setBounds(10, 157, 89, 23);
		panelAjout.add(btnValiderExamen);
		
		/*
		 *  Zone d'affichage pour la liste des examens
		 */
		JList panelExam = new JList();
		panelExam.setBackground(new Color(173, 216, 230));		
		panelExam.setBounds(363, 0, 747, 244);
		panelAjout.add(panelExam);
		
		/*
		 * Boutton modifier examens
		 */
		JButton btnModifierExamens = new JButton("Modifier");
		btnModifierExamens.setBackground(new Color(153, 204, 255));
		btnModifierExamens.setBounds(109, 156, 97, 23);
		panelAjout.add(btnModifierExamens);
		
		btnModifierExamens.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*
		 * Boutton supprimer examens
		 */
		JButton btnSupprimerExamens = new JButton("Supprimer");
		btnSupprimerExamens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSupprimerExamens.setBackground(Color.RED); 
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimerExamens.setBackground(new Color(153, 204, 255));
			}
		});
		btnSupprimerExamens.setBackground(new Color(153, 204, 255));
		btnSupprimerExamens.setBounds(216, 156, 114, 23);
		panelAjout.add(btnSupprimerExamens);
		btnSupprimerExamens.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*
		 * Définition du boutton 'bttnMasculin'
		 */
		bttnMasculin.setBounds(157, 197, 109, 23);
		panelPatient.add(bttnMasculin);
		
		/*
		 * Définition du boutton 'bttnFeminin'
		 */
		bttnFeminin.setBounds(268, 197, 109, 23);
		panelPatient.add(bttnFeminin);
		
		// Attribution des boutton radio dans le groupe 'groupeBouttons'
		groupeBouttons.add(bttnMasculin);
		groupeBouttons.add(bttnFeminin);
		bttnMasculin.setSelected(true);
		
		
		/*
		 * Définition du boutton 'btnSupprimerPatient' pour supprimer un patient
		 */
		JButton btnSupprimerPatient = new JButton("Supprimer");
		btnSupprimerPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSupprimerPatient.setBackground(Color.RED); 
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimerPatient.setBackground(new Color(222, 184, 135)); 
			}
		});
		btnSupprimerPatient.setBackground(new Color(222, 184, 135));
		btnSupprimerPatient.setBounds(240, 262, 114, 23);
		panelPatient.add(btnSupprimerPatient);

		listPatients.setBounds(1, 1, 540, 371);
		frmMash.getContentPane().add(listPatients);
		
		/*
		 * Boutton Valider un Patient
		 */
		btnValider.setBounds(146, 228, 89, 23);
		panelPatient.add(btnValider);
		
		/*
		 * Boutton annuler un Patient
		 */
		JButton btnAnnuler = new JButton("Annuler");				// Création du boutton 'Annuler'
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnuler.setBackground(new Color(222, 184, 135));
		btnAnnuler.setBounds(141, 262, 89, 23);
		panelPatient.add(btnAnnuler);
		
		/*
		 * Boutton modifier un Patient
		 */	
		JButton btnModifierPatient = new JButton("Modifier");
		btnModifierPatient.setBackground(new Color(222, 184, 135));
		btnModifierPatient.setBounds(245, 228, 97, 23);
		panelPatient.add(btnModifierPatient);
		btnModifierPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*
		 * Ajout d'un layer pour le scrolling
		 */	
		JScrollPane scrollPane = new JScrollPane(listPatients);
		scrollPane.setBounds(541, 45, 584, 296);
		frmMash.getContentPane().add(scrollPane);

		
		
		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */
		textFieldNom.addFocusListener(new FocusAdapter() {			
			public void focusLost(FocusEvent e) {					
				saisieOk=true;													// variable pour vérifier si la saisie est OK ou pas 
				textFieldMessErreur.setVisible(false);							// desactive le champs d'affichage du message d'erreur
				if (textFieldNom.getText().equals(""))  {						// si le champs est vide
					textFieldMessErreur.setVisible(true);						// activation du champs message d'erreurs
					textFieldMessErreur.setText("Le champs Nom est vide");		// message d'erreur
					saisieOk=false;												// variable qui empeche l'enregistrement du fichier si elle est en false
				}
				else {															//sinon 
					textFieldMessErreur.setVisible(false);						// desactive le champs d'affichage du message d'erreur
					String verif = textFieldNom.getText();						// la variable verif contient la saisie faite par l'utilisateur
					if(!verif.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")){	// regex qui verifie le espect du format
						textFieldMessErreur.setVisible(true);					// si pas respecté, alors on affiche le champ du message d'erreur
						textFieldMessErreur.setText("Le champs Nom contient des caractères invalides");	// affichage du message d'erreur
						saisieOk=false;
						
					}
				}
			}
		});
			
		
		/*
		 * verification sur perte de focus du champs NSS
		 * 
		 */
		textFieldNSS.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldNSS.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs NSS est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldNSS.getText();
					if(!verif.matches("[0-9]+")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs NSS doit contenir uniquement des chiffres");	
						saisieOk=false;
					}
				}
				
			}
		});

		
		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */
		textFieldDateNaissance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e)  {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldDateNaissance.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs date de naissance est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldDateNaissance.getText();
					if(!verif.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs date de naissance n'est pas au bon format jj/mm/aaaa");	
						saisieOk=false;
					}
					else {
		
						// Date saisie par l'utilisateur
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
					    Date dateFormat = null;
					    
				        try {
				            dateFormat = sdf.parse(verif);
				            
				        } catch (ParseException pe) {

				            pe.printStackTrace();
				        }						
			
						Date dateJour = new Date();
			
						if (dateFormat.after(dateJour)) {
							
							textFieldMessErreur.setVisible(true);
							textFieldMessErreur.setText("La date doit être inférieure ou égale à la date du jour");	
							saisieOk=false;
						}
						
					}
				}
			}
		});

		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */
		textFieldDateEntree.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldDateEntree.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs date d'entrée est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldDateEntree.getText();
					if(!verif.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs date d'entrée n'est pas au bon format jj/mm/aaaa");	
						saisieOk=false;
					}
					else {

						// Date saisie par l'utilisateur
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
					    Date dateFormat = null;
					    
				        try {
				            dateFormat = sdf.parse(verif);
				            
				        } catch (ParseException pe) {

				            pe.printStackTrace();
				        }						
			
						Date dateJour = new Date();
			
						if (dateJour.after(dateFormat)) {
							textFieldMessErreur.setVisible(true);
							textFieldMessErreur.setText("La date  d'entrée doit être supérieure ou égale à la date du jour");	
							saisieOk=false;
						}
					}
				}
			}
		});

		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */
		textFieldDateSortie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldDateSortie.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs date de sortie est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldDateSortie.getText();
					if(!verif.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs date de sortie n'est pas au bon format jj/mm/aaaa");	
						saisieOk=false;
					}
					else {
						// Date saisie par l'utilisateur
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
					    Date dateFormat = null;
					    Date dateSortie = null;
					    
				        try {
				            dateFormat = sdf.parse(verif);
				            dateSortie = sdf.parse(textFieldDateEntree.getText());
				            
				        } catch (ParseException pe) {

				            pe.printStackTrace();
				        }						
			
					
						if (dateSortie.after(dateFormat)) {
							textFieldMessErreur.setVisible(true);
							textFieldMessErreur.setText("La date de sortie doit être supérieure à la date d'entrée.");	
							saisieOk=false;
						}
					}
				}

			}
		});

		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */
		textFieldTelephone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldTelephone.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs n° de téléphone est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldTelephone.getText();
					if(!verif.matches("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs n° de téléphone  n'est pas au bon format 00.00.00.00");	
						saisieOk=false;
					}
				}
			}
		});
		
	

		/*
		 * Affichage d'un message d'erreur sur perte de focus
		 * pour le champs nom
		 * On verifie qu'il ne soit pas vide et ne comporte
		 * pas des caratères non autorisés
		 */

		textFieldDateExam.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				saisieOk=true;
				textFieldMessErreur.setVisible(false);
				if (textFieldDateExam.getText().equals(""))  {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("Le champs date d'examens est vide");	
					saisieOk=false;
				}
				else {
					textFieldMessErreur.setVisible(false);
					String verif = textFieldDateExam.getText();
					if(!verif.matches("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$")){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs date d'examens n'est pas au bon format jj/mm/aaaa");	
						saisieOk=false;
					}
					else {

						// Date saisie par l'utilisateur
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
					    Date dateFormat = null;
					    
				        try {
				            dateFormat = sdf.parse(verif);
				            
				        } catch (ParseException pe) {

				            pe.printStackTrace();
				        }						
			
						Date dateJour = new Date();
			
						if (dateJour.after(dateFormat)) {
							textFieldMessErreur.setVisible(true);
							textFieldMessErreur.setText("La date  d'examens doit être supérieure ou égale à la date du jour");	
							saisieOk=false;
						}
					}
				}
				
			}
		});
		

		/*
		 * Evenement lorque l'on clique sur la liste des Patients
		 */
		panelExam.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent arg0) {						//quand on clique dans la fenetre de la liste des Patients
				saisieOk=true;

				textFieldMessErreur.setVisible(false);						// on masque le champs des messages d'erreurs
				int index = panelExam.getSelectedIndex();					// recupération de l'ID dans 'index'
				if (index == -1) return;									// si il n'y a rien on sort de l'evenement
				Examens affiche = (Examens)arrayExamens_temp.get(index);	// instanciation de 'affiche' pour afficher les Patients
				textFieldPatientExam.setText(arrayUtilisateur.get(affiche.getId_Patient()-1).getNom());			// recupère le Nom
				textFieldDateExam.setText(affiche.getDateExamens());											// recupère la date d'examens	
				comboBoxExamen.setSelectedItem(affiche.getTypeExamens());     									// recupère le type d'examen
			}
		});
		
		
		/*
		 * Boutton modifier un examens
		 */
		btnModifierExamens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						//quand on clique sur le boutton modifier un examens
				int index = panelExam.getSelectedIndex();						// on recupère l'index
				if (index == -1) return;										// s'il n'y a rien, on sort 
				Examens affiche = (Examens)arrayExamens_temp.get(index);		// instanciation de 'affiche' pour afficher les Patients							
				
				Examens modifExam = arrayExamens.get(affiche.getIdentifiant()-1);				
				modifExam.setId_exam(index);									// on recupere l'index
				modifExam.setExamIsDel(affiche.getExamIsDel());					// on recupere ExamIsDel pour savoir si le client est effacé ou non
				
				Object type= comboBoxExamen.getSelectedItem();					// On recupère le type d'examens de la liste
				modifExam.setTypeExamens(type);
				modifExam.setDateExamens(getTextFieldDateExam().getText());		// on recupère la date de l'examens	
				arrayExamens.set((affiche.getIdentifiant()-1),modifExam);		// selection de l'examens choisi
				panelExam.setListData(arrayExamens_temp.toArray());				// mise a jour de l'affichage				
				try {
					ecriture.EcritureExamens(arrayExamens);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	// On ecrit les infos dans le fichier d'examens
				
				
										
			}
		});
		
		/*
		 * Boutton supprimer un examens   8888888
		 */
		btnSupprimerExamens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				 	//quand on clique sur le boutton supprimer un examens
		
				int index = panelExam.getSelectedIndex();					// on recupère l'index
				if (index == -1) return;								 	// s'il n'y a rien, on sort 		
			
				arrayExamens.get((arrayExamens_temp.get(index).getIdentifiant()-1)).setExamIsDel(0);					// On met à 0 la variable setPatientIsDel pour 'effacer' le patient		
				arrayExamens_temp.remove(index);							// on supprime l'examens de la liste temporaire						
				try {
					ecriture.EcritureExamens(arrayExamens);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panelExam.setListData(arrayExamens_temp.toArray());						
			}
		});

			
	
		
		 /*
		  * on attribue à 'sexeMasculin' la valeur true quand le boutton
		  * radio "Masculin" est selectionné
		  */
		bttnMasculin.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
			sexeMasculin=true;				
			}
		});
		
		 /*
		  * on attribue à 'sexeFeminin' la valeur true quand le boutton
		  * radio "Féminin" est selectionné
		  */
		bttnFeminin.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
			sexeMasculin=false;
			}
		});
		
		/*
		 * Evenement sur boutton appuyer de la souris
		 * Lors d'un clic sur un des éléments de la Jlist, on récupère l'ID dans la Jlist
		 * puis l'enregistrement qui correspond à l'ID
		 * Ensuite, on affiche le résultat dans les champs.	
		 */

		listPatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				saisieOk=true;
				examens =  new Examens();
				textFieldMessErreur.setVisible(false);
				int index = listPatients.getSelectedIndex();					// recupération de l'ID dans 'index'
				if (index == -1) return;										// si il n'y a rien on sort de l'evenement
				Patients affiche = (Patients)arrayUtilisateur.get(index);		// instanciation de 'affiche' pour afficher les Patients
				textFieldNom.setText(affiche.getNom());							// recupère le Nom
				textFieldPrenom.setText(affiche.getPrenom());					// recupère le prenom
				textFieldNSS.setText(affiche.getNSS());							// recupère le n° de securité sociale
				textFieldDateNaissance.setText(affiche.getDateNaissance());		// recupère la date de naissance
				textFieldDateEntree.setText(affiche.getDateEntree());			// recupère la date d'entrée
				textFieldDateSortie.setText(affiche.getDateSortie());			// recupère la date de sortie
				textFieldTelephone.setText(affiche.getTelephone());				// recupère le n° de téléphone								
				textFieldPatientExam.setText(affiche.getNom());
			
				if (affiche.getTextSexe().contains("Masculin")) {				// si getTextSexe  comporte "Masculin" le bouton radio est activé sinon c'est Feminin
					bttnMasculin.setSelected(true);
				}
				if (affiche.getTextSexe().contains("Feminin")) {				// si getTextSexe  comporte "Masculin" le bouton radio est activé sinon c'est Feminin
					bttnFeminin.setSelected(true);
		
				}
				examens.setId_Patient(index+1);									// On recupère l'ID et on ajoute 1 pour la saisie suivante
				Object ID_Local = affiche.getID();	
				arrayExamens_temp = recherche_examens.recherche(arrayExamens, ID_Local,0)	;	//pour comptabiliser le nombre d'examens trouvé
				panelExam.setListData(arrayExamens_temp.toArray());				// On affiche les infos lu précédemment
			}
			
		});

			
			/*
			 * Evénement sur l'appuie du boutton 'Valider'
			 * Lorsque l'on clique sur le boutton valider, on enregistre tous les champs saisis
			 * ensuite, on efface les champs pour une eventuelle nouvelle saisie
			 * 
			 */
			
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				textFieldMessErreur.setVisible(false);
				
				if (saisieOk) {
					if (textFieldNom.getText().equals("")) {									// S'il n'y a aucun nom on sort de la méthode
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Le champs Nom est vide");	
						saisieOk=false;
						return;
					}			
					else {
						textFieldMessErreur.setVisible(false);
					}
					// instanciation du Filereader
					File fichier = null;
					FileReader fr = null;
					BufferedReader br = null;

					// initialisation du fichier 'patient.txt' puis on fait une boucle pour trouver le dernier enregistrement
					try {
						fichier = new File ("patient.txt");
						fr = new FileReader (fichier);
						br = new BufferedReader(fr);
						String line;
						while((line=br.readLine())!=null)
						{
							String[] splitArray = null;						
							splitArray = line.split(",");						
							utilisateur =new Patients();						
							memID= utilisateur.setID(Integer.parseInt(splitArray[0]));		// mets dans 'memID' les ID et à la fin de la lecture il contiendra le dernier ID
						}
						listPatients.removeAll();											// suppression de la liste des Patients de la Jlist
						listPatients.setListData(arrayUtilisateur.toArray());				// On affiche la liste mise à jour dans la Jlist


					}
					catch (IOException ex){
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Il y a un problème sur le fichier patient.txt ");	
						
					}
					utilisateur = new Patients();												// instanciation de 'utilisateur'

					/*
					 * récupération des informations du formulaire dans l'objet 'utilisateur'
					 * puis ajoute dans la Jlist "listPatients'
					 */
					utilisateur.setID(memID+1);
					utilisateur.setNom(getTextFieldNom().getText());
					utilisateur.setPrenom(getTextFieldPrenom().getText());

					if (sexeMasculin) {															
						utilisateur.setTextSexe("Masculin");   
					}
					else {
						utilisateur.setTextSexe("Feminin"); 
					}					
					utilisateur.setNSS(getTextFieldNSS().getText());				
					utilisateur.setDateNaissance(getTextFieldDateNaiss().getText());
					utilisateur.setDateEntree(getTextFieldDateEntree().getText());
					utilisateur.setDateSortie(getTextFieldDateSortie().getText());
					utilisateur.setTelephone(getTextFieldTelephone().getText());
					arrayUtilisateur.add(utilisateur);										// ajoute les infos dans l'arraylist
					listPatients.setListData(arrayUtilisateur.toArray());					// affiche le Patient ajouté dans la fenetre

					try {
						ecriture.EcriturePatients(arrayUtilisateur, listPatients);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				// On ecrit les infos de la Jlist dans le fichier
	
					/*
					 * efface les infos saisient après enregistrement
					 */
					textFieldNom.setText("");
					textFieldPrenom.setText("");
					bttnMasculin.setSelected(true);

					textFieldNSS.setText("");
					textFieldDateNaissance.setText("");
					textFieldDateEntree.setText("");
					textFieldDateSortie.setText("");
					textFieldTelephone.setText("");
					textFieldPatientExam.setText("");


				}
				else {
					textFieldMessErreur.setVisible(true);
					textFieldMessErreur.setText("ENREGISTREMENT IMPOSSIBLE, ERREUR DANS LA SAISIE");	
					saisieOk=false;
				}
			}
		});

		
		/*
		 * efface les champs quand on clique sur le boutton "Annuler"
		 */
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				bttnMasculin.setSelected(true);
				textFieldNSS.setText("");
				textFieldDateNaissance.setText("");
				textFieldDateEntree.setText("");
				textFieldDateSortie.setText("");
				textFieldTelephone.setText("");
				textFieldPatientExam.setText("");
			}
		});

		/*
		 * Evenement lorsque l'on appuie sur la boutton modifier
		 */
		btnModifierPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						// quand on clique sur le boutton modifier un patient
				saisieOk=true;
				int index = listPatients.getSelectedIndex();					// on recupère l'index selectionné
				if (index == -1) return;										// si il n'y a rien , on sort
	
				Patients modifUser = arrayUtilisateur.get(index);			
				modifUser.setID(index);											// on recupère l'index
				modifUser.setNom(getTextFieldNom().getText());					// on recupère le nom
				modifUser.setPrenom(getTextFieldPrenom().getText());			// on recupère le prenom
				modifUser.setNSS(getTextFieldNSS().getText());
				modifUser.setDateNaissance(getTextFieldDateNaiss().getText());	// on recupère lA DATE DE NAISSANCE
				modifUser.setDateEntree(getTextFieldDateEntree().getText());	// on recupère la date d'entrée
				modifUser.setDateSortie(getTextFieldDateSortie().getText());	// on recupère la date de sortie
				modifUser.setTelephone(getTextFieldTelephone().getText());		// on recupère le téléphone		
				
		
				if (sexeMasculin) {												// si le boutton radio 'Masculin' est selectionné													
					modifUser.setTextSexe("Masculin");   						// on recupere 'Mascilin"
				}
				else {															// sinon, on récupère "Feminin"
					modifUser.setTextSexe("Feminin"); 
				}
				listPatients.setListData(arrayUtilisateur.toArray());			// mise à jour de la liste
				try {
					ecriture.EcriturePatients(arrayUtilisateur, listPatients);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		

			

			btnSupprimerPatient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = listPatients.getSelectedIndex();
					if(index==-1)return;					
				
					arrayUtilisateur.get(index).setPatientIsDel(0);									// On met à 0 la variable setPatientIsDel pour 'effacer' le patient
					arrayUtilisateur_temp = (ArrayList<Patients>)arrayUtilisateur.clone();  // Clone arrayUtilisateur dans arrayUtilisateur_temp
					arrayUtilisateur_temp.remove(index);
					
					try {
						ecriture.EcriturePatients(arrayUtilisateur, listPatients);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						// On ecrit les infos de la Jlist dans le fichier
					
					//Lecture.litFichierPatients(arrayUtilisateur,"patient.txt");		// enregistre les champs dans le fichier texte
					listPatients.setListData(arrayUtilisateur_temp.toArray());
									// ExamDel passe à true pour signaler l'effacement des examens
					arrayExamens_temp = recherche_examens.recherche(arrayExamens, index+1, 1);					//pour comptabiliser le nombre d'examens trouvé
					try {
						ecriture.EcritureExamens(arrayExamens);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			// On ecrit les infos dans le fichier d'examens
					
					panelExam.setListData(arrayExamens_temp.toArray());
					
				}
			});
			btnSupprimerPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		
		btnValiderExamen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (saisieOk) {
					textFieldMessErreur.setVisible(false);
					
					if (textFieldNom.getText().equals("") || textFieldPatientExam.getText().equals("")) {									// S'il n'y a aucun nom on sort de la méthode
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("ATTENTION: Il n'y a pas de nom de selectionné, veuillez choisir un Patient.");	
						
						return;
					}			
					arrayExamens.clear();	
					lecture.litFichierExamens(arrayExamens,"examens.txt");			// enregistre les champs dans le fichier texte
			
					
					/*
					 * récupération des informations du formulaire dans 'utilisateur'
					 * puis ajouter dans la Jlist "listPatients'
					 * */
									
					examens.setId_Patient_Exam(memID+1);
					//examens.memID;
					examens.setId_Patient((examens.getId_Patient()));
					examens.setExamIsDel(1);
					
					Object type= comboBoxExamen.getSelectedItem();
					examens.setTypeExamens(type);
					examens.setDateExamens(getTextFieldDateExam().getText());				
					arrayExamens.add(examens);
					arrayExamens_temp.add(examens);
					panelExam.setListData(arrayExamens_temp.toArray());
					
					memID =arrayExamens.get(arrayExamens.size() - 1).getId_Patient_Exam();  // mémorise dernier id de examens

					/*
					 * Enregistrement des données de l'objet 'utilisateur'
					 */
					
					
					try {
						File f=new File("examens.txt");
						FileWriter fw = new FileWriter(f,false);
						BufferedWriter buffer = new  BufferedWriter(fw);					
					
						 for(Examens elem: arrayExamens) {
						
							buffer.write(elem.toString());
							buffer.newLine();
							buffer.flush();
							
						}
					buffer.close();
				}catch (IOException ex) {
					System.out.println("ERREUR: "+ex);
				}
				}
					else {
						textFieldMessErreur.setVisible(true);
						textFieldMessErreur.setText("Examen non enregistré. Il y a une erreur dans la saisie");	
						saisieOk=false;
					}
			
			}
		});
		


		/*
		 * Lecture du fichier 'patient.txt'
		 */
		lecture.litFichierPatients(arrayUtilisateur,"patient.txt");		// enregistre les champs dans le fichier texte


		listPatients.removeAll();										// On efface l'affichage de la Jlist 'listPatients'
		listPatients.setListData(arrayUtilisateur.toArray());			// On affiche les infos lu précédemment
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("./Mash-logo2.jpg"));
		lblNewLabel_1.setBounds(972, 1, 153, 43);
		frmMash.getContentPane().add(lblNewLabel_1);
		if (arrayUtilisateur.size()!=0) {								// on verifie que la base utilisateur n'est pas vide
			lecture.litFichierExamens(arrayExamens,"examens.txt");			// enregistre les champs dans le fichier texte


			if (arrayExamens.size()!=0) {	
				arrayExamens_temp = recherche_examens.recherche(arrayExamens,-1, 1);		//pour comptabiliser le nombre d'examens trouvé// on verifie que la base examens n'est pas vide
				panelExam.removeAll();													// On efface l'affichage de la Jlist 'listPatients'
				panelExam.setListData(arrayExamens_temp.toArray());						// On affiche les infos lu précédemment
				memID =arrayExamens.get(arrayExamens.size() - 1).getId_Patient_Exam();  // mémorise dernier id de examens
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(466, 88, 2, 2);
				panelAjout.add(scrollPane_1);
			}
			else {																		// sinon, on affiche le message d'erreur
				textFieldMessErreur.setVisible(true);
				textFieldMessErreur.setText("Il n'y a aucun examens de programmé");	
			}
		}
		else {																			// sinon, on affiche le message d'erreur
			textFieldMessErreur.setVisible(true);
			textFieldMessErreur.setText("LA BASE DE DONNEES EST VIDE");	

		}
		textFieldMessErreur.setVisible(false);	
		
		
		
		
		
		
		
	}
	public JTextField getTextFieldMessErreur() {
		return textFieldMessErreur;
	}

	public void setTextFieldMessErreur(JTextField textFieldMessErreur) {
		this.textFieldMessErreur = textFieldMessErreur;
	}

	public JTextField getTextFieldNom() {
		return textFieldNom;
	}

	public void setTextFieldNom(JTextField textFieldNom) {
		this.textFieldNom = textFieldNom;
	}

	public JTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}

	public void setTextFieldPrenom(JTextField textFieldPrenom) {
		this.textFieldPrenom = textFieldPrenom;
	}

	public JTextField getTextFieldNSS() {
		return textFieldNSS;
	}

	public void setTextFieldNSS(JTextField textFieldNSS) {
		this.textFieldNSS = textFieldNSS;
	}

	public JTextField getTextFieldDateNaiss() {
		return textFieldDateNaissance;
	}

	public void setTextFieldDateNaiss(JTextField textFieldDateNaiss) {
		this.textFieldDateNaissance = textFieldDateNaiss;
	}

	public JTextField getTextFieldDateEntree() {
		return textFieldDateEntree;
	}

	public void setTextFieldDateEntree(JTextField textFieldDateEntree) {
		this.textFieldDateEntree = textFieldDateEntree;
	}

	public JTextField getTextFieldDateSortie() {
		return textFieldDateSortie;
	}

	public void setTextFieldDateSortie(JTextField textFieldDateSortie) {
		this.textFieldDateSortie = textFieldDateSortie;
	}

	public JTextField getTextFieldTelephone() {
		return textFieldTelephone;
	}

	public void setTextFieldTelephone(JTextField textFieldTelephone) {
		this.textFieldTelephone = textFieldTelephone;
	}

	public JTextField getTextFieldPatientAjoutExam() {
		return textFieldPatientExam;
	}

	public void setTextFieldPatientAjoutExam(JTextField textFieldPatientAjoutExam) {
		this.textFieldPatientExam = textFieldPatientAjoutExam;
	}

	public boolean isSexeMasculin() {
		return sexeMasculin;
	}

	public void setSexeMasculin(boolean sexeMasculin) {
		this.sexeMasculin = sexeMasculin;
	}
	public JTextField getTextSexe() {
		return textSexe;
	}

	public void setTextSexe(JTextField textSexe) {
		this.textSexe = textSexe;
	}
	public JTextField getTextFieldPatientExam() {
		return textFieldPatientExam;
	}

	public void setTextFieldPatientExam(JTextField textFieldPatientExam) {
		this.textFieldPatientExam = textFieldPatientExam;
	}

	public JTextField getTextFieldDateExam() {
		return textFieldDateExam;
	}

	public void setTextFieldDateExam(JTextField textFieldDateExam) {
		this.textFieldDateExam = textFieldDateExam;
	}

	public JTextField getComboBoxExamen() {
		return comboBoxExamen;
	}
}
