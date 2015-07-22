package co.edu.udea.ps.psp.p2.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.edu.udea.ps.psp.p2.controller.Controller;
import co.edu.udea.ps.psp.p2.exception.MathModelException;

import java.awt.Color;
import java.awt.Dimension;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField pathFileText;
	private JTextField meanText;
	private JTextField standarDesviationText;
	private JList<Double> numberList;
	private Controller controller;
	
	private String pathofFile;
	
	private LinkedList<Double>numbers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		controller= new Controller();
		setTitle("Calculador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel filePanel = new JPanel();
		contentPane.add(filePanel, BorderLayout.NORTH);
		
		pathFileText = new JTextField();
		pathFileText.setBackground(Color.WHITE);
		pathFileText.setEditable(false);
		pathFileText.setEnabled(false);
		filePanel.add(pathFileText);
		pathFileText.setColumns(30);
		
		JButton btnCargarArchivo = new JButton("Cargar Archivo");
		btnCargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFileChosser();
				
			}
		});
		filePanel.add(btnCargarArchivo);
		
		JPanel numbersPanel = new JPanel();
		contentPane.add(numbersPanel, BorderLayout.CENTER);
		
		numberList = new JList<Double>();
		numberList.setLayoutOrientation(JList.VERTICAL_WRAP);
		numberList.setVisibleRowCount(-1);
		JScrollPane jScrollPane= new JScrollPane(numberList);
		jScrollPane.setPreferredSize(new Dimension(250, 80));
		numbersPanel.add(jScrollPane);
		
		
		JPanel mathPanel = new JPanel();
		contentPane.add(mathPanel, BorderLayout.SOUTH);
		
		JLabel lblMedia = new JLabel("MEDIA");
		mathPanel.add(lblMedia);
		
		meanText = new JTextField();
		meanText.setForeground(Color.BLACK);
		meanText.setBackground(Color.WHITE);
		meanText.setEnabled(false);
		meanText.setEditable(false);
		mathPanel.add(meanText);
		meanText.setColumns(10);
		
		JLabel lblDesviacinEstandar = new JLabel("Desviaci\u00F3n Estandar");
		mathPanel.add(lblDesviacinEstandar);
		
		standarDesviationText = new JTextField();
		standarDesviationText.setForeground(Color.BLACK);
		standarDesviationText.setBackground(Color.WHITE);
		standarDesviationText.setEnabled(false);
		standarDesviationText.setEditable(false);
		mathPanel.add(standarDesviationText);
		standarDesviationText.setColumns(10);
		
		JPanel calcularPanel = new JPanel();
		contentPane.add(calcularPanel, BorderLayout.EAST);
		
		JButton btnCalcularValores = new JButton("Calcular Valores");
		btnCalcularValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showResults();
			}
		});
		GroupLayout gl_calcularPanel = new GroupLayout(calcularPanel);
		gl_calcularPanel.setHorizontalGroup(
			gl_calcularPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calcularPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCalcularValores)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_calcularPanel.setVerticalGroup(
			gl_calcularPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calcularPanel.createSequentialGroup()
					.addGap(65)
					.addComponent(btnCalcularValores)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		calcularPanel.setLayout(gl_calcularPanel);
	}

	protected void showResults() {
		if (getNumbers()!=null) {
			try {
				standarDesviationText.setText(
						String.valueOf(
								controller.calculateStandardDesviation(
										getNumbers())));
				meanText.setText(
						String.valueOf(
								controller.calculateMean(
										getNumbers())));
			} catch (MathModelException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	protected void openFileChosser() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter extensionFilter = new 
				FileNameExtensionFilter("Archivos de texto", "txt");
		chooser.setFileFilter(extensionFilter);
		int returnValue= chooser.showOpenDialog(this);
		if (returnValue== JFileChooser.APPROVE_OPTION) {
			this.setPathofFile(chooser.getSelectedFile().getAbsolutePath());
			pathFileText.setText(this.getPathofFile());
			meanText.setText("");
			standarDesviationText.setText("");
			
			chargeListOfNumbers(this.getPathofFile());
		}
	}

	private void chargeListOfNumbers(String pathofFile2) {
		try {
			numbers=controller.getnumbersFromFile(this.getPathofFile());
			Double[] numbersData= new Double[numbers.size()];
			for (int i = 0; i < numbers.size(); i++) {
				numbersData[i]=numbers.get(i);
				
			}
			numberList.setListData(numbersData);
		} catch (MathModelException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			pathFileText.setText("");
		}
		
	}

	public String getPathofFile() {
		return pathofFile;
	}

	public void setPathofFile(String pathofFile) {
		this.pathofFile = pathofFile;
	}

	public LinkedList<Double> getNumbers() {
		return numbers;
	}

	public void setNumbers(LinkedList<Double> numbers) {
		this.numbers = numbers;
	}
	
	
	
	

}
