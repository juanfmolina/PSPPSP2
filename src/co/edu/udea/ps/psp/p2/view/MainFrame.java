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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import co.edu.udea.ps.psp.p2.controller.Controller;
import co.edu.udea.ps.psp.p2.exception.CounterModelException;
import co.edu.udea.ps.psp.p2.model.ClassComponent;
import co.edu.udea.ps.psp.p2.model.MethodComponent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField pathFileText;
	private Controller controller;

	private String pathofFile;

	private LinkedList<ClassComponent> classes;
	private JTable jComponentTable;
	private JTextField textNumberOfLines;

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
		controller = new Controller();
		setTitle("Calculador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 585);
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

		JPanel componentPanel = new JPanel();
		contentPane.add(componentPanel, BorderLayout.CENTER);
		jComponentTable = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(jComponentTable);
		componentPanel.add(scrollPane);


		JPanel calcularPanel = new JPanel();
		contentPane.add(calcularPanel, BorderLayout.EAST);

		JButton btnCalcularLineas = new JButton("Calcular Lineas de código");
		btnCalcularLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showResults();
			}
		});
		GroupLayout gl_calcularPanel = new GroupLayout(calcularPanel);
		gl_calcularPanel
				.setHorizontalGroup(gl_calcularPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_calcularPanel.createSequentialGroup().addContainerGap()
								.addComponent(btnCalcularLineas)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_calcularPanel.setVerticalGroup(gl_calcularPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calcularPanel.createSequentialGroup().addGap(65).addComponent(btnCalcularLineas)
						.addContainerGap(67, Short.MAX_VALUE)));
		calcularPanel.setLayout(gl_calcularPanel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel jbTotalOfLines = new JLabel("Total de lineas de c\u00F3digo\r\n");
		jbTotalOfLines.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(jbTotalOfLines);
		
		textNumberOfLines = new JTextField();
		textNumberOfLines.setEnabled(false);
		textNumberOfLines.setEditable(false);
		textNumberOfLines.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(textNumberOfLines);
		textNumberOfLines.setColumns(10);
	}

	protected void showResults() {
		if (getClasses() != null) {
			String[]columnNames={"Nombre del componente", "Numero de lineas de código"};
			int totalOfLinesOfCode=0;
			int numberOfRows= calculateNumberOfRows(getClasses());
			String[][] tableData= new String[numberOfRows][2];
			int i=0;
			for (ClassComponent classComponent:getClasses()) {
				tableData[i][0]=classComponent.getNameOfClass();
				tableData[i][1]=String.valueOf(classComponent.getNumberOfLinesOfCode());
				i+=1;
				for(MethodComponent methodComponent: classComponent.getMethods()){
					tableData[i][0]= "        "+methodComponent.getNameOfComponent();
					tableData[i][1]=String.valueOf("        "+methodComponent.getNumberOfLinesOfCode());
					i++;
				}
				totalOfLinesOfCode+=classComponent.getNumberOfLinesOfCode();
			}
			TableModel tableModel= new DefaultTableModel(tableData, columnNames);
			jComponentTable.setModel(tableModel);
			jComponentTable.getColumnModel().getColumn(0).setPreferredWidth(400);
			jComponentTable.getColumnModel().getColumn(1).setPreferredWidth(140);
			textNumberOfLines.setText(String.valueOf(totalOfLinesOfCode));
			
			
		}

	}

	private int calculateNumberOfRows(LinkedList<ClassComponent> classes2) {
		int totalNumberOfRows=0;
		for (ClassComponent classComponent : classes2) {
			totalNumberOfRows+=classComponent.getMethods().size()+1;
		}
		
		return totalNumberOfRows;
	}

	protected void openFileChosser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = chooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			this.setPathofFile(chooser.getSelectedFile().getAbsolutePath());
			pathFileText.setText(this.getPathofFile());

			chargeListOfComponents(this.getPathofFile());
		}
	}

	private void chargeListOfComponents(String pathofFile2) {
		try {
			classes = controller.getCountedClasses(pathofFile2);
		} catch (CounterModelException e) {
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

	public LinkedList<ClassComponent> getClasses() {
		return classes;
	}

	public void setClasses(LinkedList<ClassComponent> classes) {
		this.classes = classes;
	}
}
