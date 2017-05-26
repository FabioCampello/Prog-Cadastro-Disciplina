//******************************************************
//Autor...........: Fábio Francisco Campêlo
//******************************************************

package br.com.campello.fabio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.campello.fabio.model.Curso;

public class Development extends JFrame {

	// *************************************************************************************************************************************
	// DECLARAÇÃO DE COMPONENTES DA TELA
	// *************************************************************************************************************************************
	private JPanel contentPane;
	DefaultTableModel modelo = new DefaultTableModel();

	// *************************************************************************************************************************************
	// MÉTODO PRINCIPAL MAIN
	// *************************************************************************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Development frame = new Development();
					frame.setVisible(true);
				} catch (Exception e) {

					String msg = "Erro! Tipo " + e.getMessage();
					System.out.println(msg);
				}
			}

		});
	}

	// *************************************************************************************************************************************
	// CONSTRUTOR DA JANELA
	// *************************************************************************************************************************************
	public Development() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 360);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLUE);
		panelTitulo.setBounds(10, 11, 524, 65);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);

		JLabel lblDisciplinas = new JLabel("Cursos");
		lblDisciplinas.setForeground(Color.WHITE);
		lblDisciplinas.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
		lblDisciplinas.setBounds(218, 11, 150, 32);
		panelTitulo.add(lblDisciplinas);

		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panelCampos.setBounds(10, 87, 524, 193);
		contentPane.add(panelCampos);
		panelCampos.setLayout(null);

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO DISCIPLINAS
		// ******************************************************************************************************************************
		JButton btnDisciplinas = new JButton("Disciplinas");
		btnDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new CadastroDisciplina().setVisible(true);
				dispose();

			}
		});
		btnDisciplinas.setBounds(166, 157, 100, 25);
		panelCampos.add(btnDisciplinas);

		// ******************************************************************************************************************************
		// EVENTO DO BOTÃO CURSOS
		// ******************************************************************************************************************************
		JButton btnCursos = new JButton("Cursos");
		btnCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new CadastroCurso().setVisible(true);
				dispose();

			}
		});
		btnCursos.setBounds(276, 157, 100, 25);
		panelCampos.add(btnCursos);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 504, 135);
		panelCampos.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aplicativo desenvolvido  com o prop\u00F3sito acad\u00EAmico.\r\n");
		lblNewLabel.setToolTipText("\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 350, 30);
		panel.add(lblNewLabel);
		
		JLabel lblComContdoBsico = new JLabel("Com cont\u00E9do b\u00E1sico de um CRUD!");
		lblComContdoBsico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComContdoBsico.setBounds(10, 42, 350, 30);
		panel.add(lblComContdoBsico);
		
		JLabel lblDesenvolvidoPorFbio = new JLabel("Desenvolvido por: F\u00E1bio Camp\u00EAllo");
		lblDesenvolvidoPorFbio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesenvolvidoPorFbio.setBounds(10, 76, 350, 30);
		panel.add(lblDesenvolvidoPorFbio);


	}

}
